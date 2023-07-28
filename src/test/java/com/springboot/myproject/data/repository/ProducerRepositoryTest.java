package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Producer;
import com.springboot.myproject.data.entity.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProducerRepositoryTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    /*
    테스트 데이터 생성하는 부분을 별도 메서드로 구현했기 때문에
    리포지토리를 사용하게 되면 트랙잭션이 끊어져 Producer 엔티티에서 Product 리스트를 가져오는 작업이 불가능해짐.
    */
    void relationshipTest() {
        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        System.out.println(producerRepository.findById(1L).get().getProductList());
    }
    private Product saveProduct(String name, Integer price, Integer stock) {
        Product product = new Product(name, price, stock);

        return productRepository.save(product);
    }
    private Producer saveProducer(String name){
        Producer producer = new Producer();
        producer.setName(name);

        return producerRepository.save(producer);
    }

    @Test
    @Transactional
    void relationshipTest2(){
        Product product1 = saveProduct("동글펜", 500, 1000);
        Product product2 = saveProduct("네모 공책", 100, 2000);
        Product product3 = saveProduct("지우개", 152, 1234);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("wikibooks");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1,producer2));
        productRepository.saveAll(Lists.newArrayList(product1,product2,product3));

        System.out.println("products : "+ producerRepository.findById(1L).get().getProductList());
        System.out.println("producers : " + productRepository.findById(2L).get().getProducerList());
    }
}
