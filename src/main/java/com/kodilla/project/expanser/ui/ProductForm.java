package com.kodilla.project.expanser.ui;

import com.kodilla.project.expanser.backend.entity.Category;
import com.kodilla.project.expanser.backend.entity.Shop;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ProductForm extends FormLayout {
    TextField name = new TextField("Name");
    NumberField price = new NumberField("Price");
    ComboBox<Shop> shop = new ComboBox<>("Shop");
    ComboBox<Category> category = new ComboBox<>("Category");
    DatePicker date = new DatePicker("Bought At");

    Button save =new Button("Save");
    Button delete =new Button("Delete");
    Button cancel =new Button("Cancel");

    public ProductForm(List<Shop> shops, List<Category> categories) {
        addClassName("product-form");

        shop.setItems(shops);
        shop.setItemLabelGenerator(Shop::getName);
        category.setItems(categories);
        category.setItemLabelGenerator(Category::getName);

        add(
                name,
                price,
                shop,
                category,
                date,
                generateButtonsLayout()
        );
    }

    private Component generateButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }
}
