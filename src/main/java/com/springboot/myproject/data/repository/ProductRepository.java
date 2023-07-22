package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface ProductRepository extends JpaRepository<Product,Long>{
    // Asc : 오름차순, Desc : 내림차순
    //List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByName(String name, Sort sort);

    //Paging
    Page<Product> findByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product AS p WHERE p.name= :name")
    List<Product> findByNameParam(@Param("name") String name);

    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}