package com.springboot.myproject.data.repository.support;

import com.springboot.myproject.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);
}
