package com.springboot.myproject.data.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Setter
@Getter
@Table(name = "product_detail")
@Entity
public class ProductDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne //(optional = false) = null을 허용하지 않겠다.
    @JoinColumn(name = "product_number") //매핑할 외래키 설정
    private Product product;
}
