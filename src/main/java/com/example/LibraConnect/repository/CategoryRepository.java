package com.example.LibraConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LibraConnect.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
