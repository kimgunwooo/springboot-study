package com.springboot.myproject.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @OneToOne(mappedBy = "product") //양방향 매핑 , mappedBy - ProductDetail 엔티티가 Product 엔티티의 주인이 됨.
    @ToString.Exclude //순환참조가 발생되어 StackOverflowError 발생 가능하기에 제외
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name="provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producerList = new ArrayList<>();

    public void addProducer(Producer producer){
        this.producerList.add(producer);
    }

    @Builder
    public Product(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
