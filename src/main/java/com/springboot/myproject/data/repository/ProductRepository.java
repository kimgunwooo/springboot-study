package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product,Long>{
}
