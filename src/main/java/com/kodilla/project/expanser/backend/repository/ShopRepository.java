package com.kodilla.project.expanser.backend.repository;

import com.kodilla.project.expanser.backend.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
