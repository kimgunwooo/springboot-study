package com.springboot.myproject.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="provider")
public class Provider extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="provider", fetch = FetchType.EAGER) //즉시 로딩 방식
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>(); //여러 상품 엔티티가 포함될 수 있기에
}
