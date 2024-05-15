package com.example.demo.entities;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")

public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "price", nullable = false)
    private BigDecimal price;

    @Column (name = "category", nullable = false)
    private String category;

    @Column (name = "images", nullable = false)
    private String images;

    

    


}
