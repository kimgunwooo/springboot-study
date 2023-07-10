package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제로 사용하고 있는 데이터베이스로 테스트
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void save(){
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.save(product);

        Assertions.assertEquals(product.getName(),savedProduct.getName());
        Assertions.assertEquals(product.getPrice(),savedProduct.getPrice());
        Assertions.assertEquals(product.getStock(),savedProduct.getStock());
    }
}
