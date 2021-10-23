package com.kodilla.project.expanser.ui;

import com.kodilla.project.expanser.backend.entity.Product;
import com.kodilla.project.expanser.backend.service.ExpanserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@PWA(name = "Expanse Tracker", shortName = "Expanse Tracker", enableInstallPrompt = false)
@PageTitle("Expanse Tracker")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@Route("")
public class ListView extends VerticalLayout {
    private final ExpanserService service;
    Grid<Product> grid = new Grid<>(Product.class);
    TextField filterProducts = new TextField();
    ProductForm form;

    public ListView(ExpanserService service) {
        this.service = service;
        addClassName("expanse-tracker-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllProducts(filterProducts.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }

    private void configureForm() {
        form = new ProductForm(service.findAllShops(), service.findAllCategories());
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filterProducts.setPlaceholder("Find products by name");
        filterProducts.setClearButtonVisible(true);
        filterProducts.setValueChangeMode(ValueChangeMode.LAZY);
        filterProducts.addValueChangeListener(e -> updateList());

        Button addProductButton = new Button("Add product");

        HorizontalLayout toolbar = new HorizontalLayout(filterProducts, addProductButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("name", "price");
        grid.addColumn(product -> product.getShop().getName()).setHeader("Shop");
        grid.addColumn(product -> product.getCategory().getName()).setHeader("Category");
        grid.addColumn("date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
