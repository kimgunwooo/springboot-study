package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
    // Asc : 오름차순, Desc : 내림차순
    //List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByName(String name, Sort sort);

    //Paging
    Page<Product> findByName(String name, Pageable pageable);
}