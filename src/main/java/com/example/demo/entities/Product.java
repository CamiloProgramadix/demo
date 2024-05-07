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
@Table(name = "Product")

public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "price")
    private BigDecimal price;

    @Column (name = "category")
    private String category;

    @Column (name = "images")
    private String images;



    

    


}
