package com.kodilla.project.expanser.backend.repository;

import com.kodilla.project.expanser.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE lower(i.name) like lower(concat('%', :searchTerm, '%'))")
    List<Item> search(@Param("searchTerm") String searchTerm);
}
