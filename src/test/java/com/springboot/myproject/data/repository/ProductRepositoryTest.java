package com.springboot.myproject.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.QProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제로 사용하고 있는 데이터베이스로 테스트
@EnableJpaAuditing
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired EntityManager entityManager;
    JPAQueryFactory jpaQueryFactory;
    @BeforeEach
    public void init(){
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    @Test
    void save(){
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.save(product);

        System.out.println("productName : "+ savedProduct.getName());
        System.out.println("createdAt :" + savedProduct.getCreatedAt());
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

        Product product2 = new Product();
        product2.setName("pen");
        product2.setPrice(5000);
        product2.setStock(300);

        Product product3 = new Product();
        product3.setName("pen");
        product3.setPrice(500);
        product3.setStock(50);

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

    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList){
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Number : "+product.getNumber());
            System.out.println("Product Name : "+product.getName());
            System.out.println("Product Price : "+product.getPrice());
            System.out.println("Product Stock : "+product.getStock());
            System.out.println();
            System.out.println("-------------------");
        }
    }

    @Test
    void queryDslTest2(){
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory
                .selectFrom(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList){
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Number : "+product.getNumber());
            System.out.println("Product Name : "+product.getName());
            System.out.println("Product Price : "+product.getPrice());
            System.out.println("Product Stock : "+product.getStock());
            System.out.println();
            System.out.println("-------------------");
        }
    }

    @Test
    void queryDslTest3(){
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList){
            System.out.println("-------------------");
            System.out.println("Product Name : "+product);
            System.out.println("-------------------");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name,qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple product : tupleList){
            System.out.println("-------------------");
            System.out.println("Product Name : "+product.get(qProduct.name));
            System.out.println("Product Price : "+product.get(qProduct.price));
            System.out.println("-------------------");
        }
    }



    @Test
    void queryDslTest4(){
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("pen"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList){
            System.out.println("-------------------");
            System.out.println("Product Name : "+product);
            System.out.println("-------------------");
        }
    }
    @Test
    void findByNameTest(){
        List<Product> productList = productRepository.findByName("pen");

        for(Product product : productList){
            System.out.println("Product Number : "+product.getNumber());
            System.out.println("Product Name : "+product.getName());
            System.out.println("Product Price : "+product.getPrice());
            System.out.println("Product Stock : "+product.getStock());
        }
    }
}
