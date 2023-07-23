package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndTest1(){
        Product product = new Product();
        product.setName("SpringBoot JPA");
        product.setPrice(5000);
        product.setStock(500);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("A book to see Spring Boot and JPA together");

        productDetailRepository.save(productDetail);

        //생성한 데이터 조회
        /*
        ProductDetail에서 Product 객체를 1대1 단방향 연관관계를 설정했기 때문에
        ProductDetailRepository에서 ProductDetail 객체를 조회한 후 연관 매핑된 Product 객체를 조회할 수 있다.
         */

        System.out.println("savedProduct : " + productDetailRepository.findById(productDetail.getId()).get().getProduct());
        System.out.println("savedProductDetail : " + productDetailRepository.findById(productDetail.getId()).get());
    }
}
