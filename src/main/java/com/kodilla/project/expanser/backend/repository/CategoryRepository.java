package com.kodilla.project.expanser.backend.repository;

import com.kodilla.project.expanser.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
