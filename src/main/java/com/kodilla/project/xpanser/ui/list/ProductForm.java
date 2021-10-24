package com.kodilla.project.xpanser.ui.list;

import com.kodilla.project.xpanser.backend.entity.Category;
import com.kodilla.project.xpanser.backend.entity.Product;
import com.kodilla.project.xpanser.backend.entity.Shop;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ProductForm extends FormLayout {
    Binder<Product> binder = new BeanValidationBinder<>(Product.class);

    TextField name = new TextField("Name");
    NumberField price = new NumberField("Price");
    ComboBox<Shop> shop = new ComboBox<>("Shop");
    ComboBox<Category> category = new ComboBox<>("Category");
    DatePicker date = new DatePicker("Bought At");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");
    private Product product;

    public ProductForm(List<Shop> shops, List<Category> categories) {
        addClassName("product-form");
        binder.bindInstanceFields(this);

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

    public void setProduct(Product product) {
        this.product = product;
        binder.readBean(product);
    }

    private Component generateButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSaveProduct());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, product)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSaveProduct() {
        try {
            binder.writeBean(product);
            fireEvent(new SaveEvent(this, product));
        } catch (ValidationException e) {
            // TODO: Implement ui for error notification
            e.printStackTrace();
        }
    }

    public static abstract class ProductFormEvent extends ComponentEvent<ProductForm> {
        private Product product;

        private ProductFormEvent(ProductForm source, Product product) {
            super(source, false);
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }

    }

    public static class SaveEvent extends ProductFormEvent {
        SaveEvent(ProductForm source, Product product) {
            super(source, product);
        }
    }

    public static class DeleteEvent extends ProductFormEvent {
        DeleteEvent(ProductForm source, Product product) {
            super(source, product);
        }
    }

    public static class CloseEvent extends ProductFormEvent {
        CloseEvent(ProductForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(
            Class<T> eventType,
            ComponentEventListener<T> listener
    ) {
        return getEventBus().addListener(eventType, listener);
    }
}
