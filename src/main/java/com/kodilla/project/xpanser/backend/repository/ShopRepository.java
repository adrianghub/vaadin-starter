package com.kodilla.project.xpanser.backend.repository;

import com.kodilla.project.xpanser.backend.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
