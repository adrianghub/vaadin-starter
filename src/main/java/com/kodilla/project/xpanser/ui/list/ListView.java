package com.kodilla.project.xpanser.ui.list;

import com.kodilla.project.xpanser.backend.entity.Product;
import com.kodilla.project.xpanser.backend.service.XpanserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;

@CssImport("./styles/shared-styles.css")
@PageTitle("Product List | Xpanser")
public class ListView extends VerticalLayout {
    private final XpanserService service;
    Grid<Product> grid = new Grid<>(Product.class);
    TextField filterProducts = new TextField();
    ProductForm form;

    public ListView(XpanserService service) {
        this.service = service;
        addClassName("xpanser-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        updateList();

        closeProductForm();
    }

    private void closeProductForm() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("active");
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

        form.addListener(ProductForm.SaveEvent.class, this::saveProduct);
        form.addListener(ProductForm.DeleteEvent.class, this::deleteProduct);
        form.addListener(ProductForm.CloseEvent.class, e -> closeProductForm());
    }

    private void deleteProduct(ProductForm.DeleteEvent event) {
        service.deleteProduct(event.getProduct());
        updateList();
        closeProductForm();
    }

    private void saveProduct(ProductForm.SaveEvent event) {
        service.saveProduct(event.getProduct());
        updateList();
        closeProductForm();
    }

    private Component getToolbar() {
        filterProducts.setPlaceholder("Find products by name");
        filterProducts.setClearButtonVisible(true);
        filterProducts.setValueChangeMode(ValueChangeMode.LAZY);
        filterProducts.addValueChangeListener(e -> updateList());

        Button addProductButton = new Button("Add product");
        addProductButton.addClickListener(e -> addProduct());

        HorizontalLayout toolbar = new HorizontalLayout(filterProducts, addProductButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addProduct() {
        grid.asSingleSelect().clear();
        editProduct(new Product());
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("name", "price");
        grid.addColumn(product -> product.getShop().getName()).setHeader("Shop");
        grid.addColumn(product -> product.getCategory().getName()).setHeader("Category");
        grid.addColumn("date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e -> editProduct(e.getValue()));
    }

    private void editProduct(Product product) {
        if (product == null) {
            closeProductForm();
        } else {
            form.setProduct(product);
            form.setVisible(true);
            addClassName("active");
        }
    }
}
