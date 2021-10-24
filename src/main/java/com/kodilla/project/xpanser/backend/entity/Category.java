package com.kodilla.project.xpanser.backend.entity;


import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category extends AbstractEntity {

    @NotEmpty
    private String name;

    public Category() {
    }

    @Formula("(SELECT SUM(p.price) FROM Product p WHERE p.category_id = id)")
    private Double totalSpent;

    public Double getTotalSpent() {
        return totalSpent;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
