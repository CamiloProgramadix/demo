package com.example.demo.Mapper;

import com.example.demo.controllers.dto.ProductDTO;
import com.example.demo.controllers.dto.UserDTO;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;

public class MapperConvert {
    public UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .last_name(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    public ProductDTO convertProductToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .images(product.getImages())
                .build();
    }

    public User convertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLast_name())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(userDTO.getRoles())
                .build();
    }

    public Product convertProductDTOToProduct(ProductDTO productDTO){
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .images(productDTO.getImages())
                .build();


        }
    }
