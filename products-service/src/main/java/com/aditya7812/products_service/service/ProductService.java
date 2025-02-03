package com.aditya7812.products_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aditya7812.products_service.dto.ProductDTO;
import com.aditya7812.products_service.model.Product;
import com.aditya7812.products_service.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepo;

    public Product createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());

        return productRepo.save(product);
    }
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setPrice(product.getPrice());
            dto.setStockQuantity(product.getStockQuantity());
            dto.setCategory(product.getCategory());
            dto.setImageUrl(product.getImageUrl());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

}
