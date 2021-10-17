package com.kodilla.project.expanser.backend.repository;

import com.kodilla.project.expanser.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
