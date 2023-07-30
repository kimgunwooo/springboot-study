package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                        .orElseThrow(RuntimeException::new).getProvider()
        );
    }

    @Test
    void relationshipTest(){
        //테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(20000);
        product2.setStock(200);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(1000);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = providerRepository.findById(provider.getId()).get()
                .getProductList();
        //테스트
        for(Product product : productList){
            System.out.println(product);
        }
    }

    //영속성 전이 테스트
    @Test
    void cascadeTest(){
        Provider provider = savedProvider("새로운 공급 업체");

        Product product1 = savedProduct("상품1",1000,1000);
        Product product2 = savedProduct("상품2",500,1500);
        Product product3 = savedProduct("상품3",750,500);

        //연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1,product2,product3));

        //부모 엔티티가 되는 Provider 엔티티만 저장하면 Cascade.PERSIST에 맞춰 Product 엔티티도 함께 저장
        providerRepository.save(provider);
    }
    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }
    private Product savedProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    //고아 객체의 제거 기능 테스트
    @Test
    @Transactional
    void orphanRemovalTest(){
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1",1000,1000);
        Product product2 = savedProduct("상품2",500,1500);
        Product product3 = savedProduct("상품3",750,500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1,product2,product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);
    }
}
