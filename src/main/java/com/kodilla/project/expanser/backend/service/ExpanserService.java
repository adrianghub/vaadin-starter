package com.kodilla.project.expanser.backend.service;

import com.kodilla.project.expanser.backend.entity.Category;
import com.kodilla.project.expanser.backend.entity.Item;
import com.kodilla.project.expanser.backend.entity.Shop;
import com.kodilla.project.expanser.backend.repository.CategoryRepository;
import com.kodilla.project.expanser.backend.repository.ItemRepository;
import com.kodilla.project.expanser.backend.repository.ShopRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExpanserService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final CategoryRepository categoryRepository;

    public ExpanserService(
            ItemRepository itemRepository,
            ShopRepository shopRepository,
            CategoryRepository categoryRepository
    ) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Item> findAllItems(String itemFilter) {
        if (itemFilter == null || itemFilter.isEmpty()) {
            return itemRepository.findAll();
        } else {
            return itemRepository.search(itemFilter);
        }
    }

    public long countItems() {
        return itemRepository.count();
    }

    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    public void saveContact(Item item) {
        if (item == null) {
            System.err.println("Cannot find item. Are you sure you have connected your form to the application?");
            return;
        }
        itemRepository.save(item);
    }

    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @PostConstruct
    public void loadData() {

        if (shopRepository.count() == 0) {
            shopRepository.saveAll(
                    Stream.of("Biedronka,", "Lidl", "Orlen", "Spoldzienia mieszkaniowa", "Spotify")
                            .map(Shop::new)
                            .collect(Collectors.toList()));
        }

        if (categoryRepository.count() == 0) {
            categoryRepository
                    .saveAll(Stream.of("Groceries", "Gifts", "Holidays", "Car", "Rent", "Subscriptions")
                            .map(Category::new).collect(Collectors.toList()));
        }

        if (itemRepository.count() == 0) {
            Random r = new Random(0);
            List<Shop> shops = shopRepository.findAll();
            List<Category> categories = categoryRepository.findAll();
            itemRepository.saveAll(
                    Stream.of("Vacuum 13.40", "Knife 2.40")
                            .map(properties -> {
                                String[] split = properties.split(" ");
                                Item item = new Item();
                                item.setName(split[0]);
                                item.setPrice(Double.parseDouble(split[1]));
                                item.setShop(shops.get(r.nextInt(shops.size())));
                                item.setCategory(categories.get(r.nextInt(categories.size())));
                                return item;
                            }).collect(Collectors.toList()));
        }
    }

}