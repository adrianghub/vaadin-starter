package com.kodilla.project.xpanser.backend.repository;

import com.kodilla.project.xpanser.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
