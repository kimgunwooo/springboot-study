package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest //@Transactional 어노테이션을 포함해서 테스트 코드 종료 시 DB 자동 롤백
// 기본적으로 임베디드 DB 사용. 다른 DB 사용 시 별도의 설정
public class ProductRepositoryTestByH2 {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){
        //given
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        //when
        Product savedProduct = productRepository.save(product);

        //then
        Assertions.assertEquals(product.getName(),savedProduct.getName());
        Assertions.assertEquals(product.getPrice(),savedProduct.getPrice());
        Assertions.assertEquals(product.getStock(),savedProduct.getStock());
    }

    @Test
    void selectTest(){
        //given
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when - 조회 메서드 호출
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        //then
        Assertions.assertEquals(product.getName(),foundProduct.getName());
        Assertions.assertEquals(product.getPrice(),foundProduct.getPrice());
        Assertions.assertEquals(product.getStock(),foundProduct.getStock());
    }
}
