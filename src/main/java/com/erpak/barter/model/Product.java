package com.erpak.barter.model;

import com.erpak.barter.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "barter_point")
    private Long barterPoint;

    @Column(name = "description")
    private String description;

    @ElementCollection(targetClass = ProductStatus.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_statuses", joinColumns = @JoinColumn(name = "product_id"))
    private Set<ProductStatus> statuses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
