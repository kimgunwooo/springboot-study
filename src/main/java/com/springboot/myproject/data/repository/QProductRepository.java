package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//ProductRepository에서 사용해도 되지만, 구분
public interface QProductRepository extends JpaRepository<Product, Long> , QuerydslPredicateExecutor<Product> {
}
