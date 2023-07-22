package com.springboot.myproject.data.repository.support;

import com.springboot.myproject.data.entity.Product;
import com.springboot.myproject.data.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    //QuerydslRepositorySupport을 상속받으면 부모 클래스에 도메인을 전달해야 함.
    public ProductRepositoryCustomImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        QProduct product = QProduct.product;

        List<Product> productList = from(product)
                .where(product.name.eq(name))
                .select(product)
                .fetch();

        return productList;
    }
}
