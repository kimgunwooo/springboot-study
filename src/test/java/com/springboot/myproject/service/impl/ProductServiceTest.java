package com.springboot.myproject.service.impl;

import com.springboot.myproject.data.dto.ProductDto;
import com.springboot.myproject.data.dto.ProductResponseDto;
import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.repository.ProductRepository;
import com.springboot.myproject.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
public class ProductServiceTest {
    //private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    @MockBean
    ProductRepository productRepository;

    //private ProductServiceImpl productService;
    @Autowired
    ProductService productService;

    /*
    @BeforeEach
    public void setUpTest(){
        productService = new ProductServiceImpl(productRepository);
    }
    */

    @Test
    void getProductTest(){
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("pen");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);


        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L); //부가 검증
    }

    @Test
    void saveProductTest(){
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("pen", 1000, 1234)
        );

        Assertions.assertEquals(productResponseDto.getName(),"pen");
        Assertions.assertEquals(productResponseDto.getPrice(),1000);
        Assertions.assertEquals(productResponseDto.getStock(),1234);

        verify(productRepository).save(any());
    }
}
