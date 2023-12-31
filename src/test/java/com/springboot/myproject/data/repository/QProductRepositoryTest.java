package com.springboot.myproject.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryDSLTest1(){
        Predicate predicate = QProduct.product.name.containsIgnoreCase("pen")
                .and(QProduct.product.price.between(1000,2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()){
            Product product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
    @Test
    public void queryDSLTest2(){
        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("pen")
                        .and(qProduct.price.between(550,1500))
        );

        for(Product product : productList){
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
}
