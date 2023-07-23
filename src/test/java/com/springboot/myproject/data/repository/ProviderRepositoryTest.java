package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1(){
        //테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        //테스트
        System.out.println(
                "product : " + productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new));
        System.out.println(
                "provider : " + productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new).getProvider();
        );
    }
}
