package com.kodilla.project.expanser.backend.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Shop extends AbstractEntity {
    private String name;

    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private List<Product> products = new LinkedList<>();

    public Shop() {}

    @Formula("(SELECT COUNT(p.id) FROM Product p WHERE p.shop_id = id)")
    private int productCount;

    public int getProductCount() {
        return productCount;
    }

    public Shop(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
