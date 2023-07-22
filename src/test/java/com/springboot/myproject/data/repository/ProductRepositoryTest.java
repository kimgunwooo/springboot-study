package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;

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

    @Test
    void sortingAndPagingTest(){
        Product product1 = new Product();
        product1.setName("pen");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("pen");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("pen");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        //sorting
        productRepository.findByName("pen", Sort.by(Order.asc("price")));
        productRepository.findByName("pen",Sort.by(Order.asc("price"),Order.desc("stock")));

        System.out.println(productRepository.findByName("pen",getSort()));

        //Paging
        Page<Product> productPage = productRepository.findByName("pen", PageRequest.of(0,2));
        System.out.println(productPage); //몇 번째 페이지에 해당하는지만 확인 가능
        System.out.println(productPage.getContent()); //세부적인 값 확인
    }

    private Sort getSort(){
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }
}
