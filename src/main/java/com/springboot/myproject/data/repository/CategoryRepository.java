package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
