package com.kodilla.project.expanser.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Shop extends AbstractEntity {
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private List<Item> items = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
