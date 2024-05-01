package com.example.demo.controllers.dto;

import java.time.LocalDateTime;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BuyDTO {

    private Long id;
    private LocalDateTime date;
    private UserDTO user;
    private ProductDTO product;
    
}