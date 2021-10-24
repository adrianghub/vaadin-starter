package com.kodilla.project.xpanser.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Product extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @NotNull
    private Double price = 0.0;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @NotNull
    private com.kodilla.project.xpanser.backend.entity.Shop shop;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public com.kodilla.project.xpanser.backend.entity.Shop getShop() {
        return shop;
    }

    public void setShop(com.kodilla.project.xpanser.backend.entity.Shop shop) {
        this.shop = shop;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
