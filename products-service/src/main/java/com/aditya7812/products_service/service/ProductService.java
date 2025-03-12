package com.aditya7812.products_service.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aditya7812.products_service.dto.AddQuantityDTO;
import com.aditya7812.products_service.dto.ProductDTO;
import com.aditya7812.products_service.dto.StockProduct;
import com.aditya7812.products_service.dto.StockRequestDTO;
import com.aditya7812.products_service.model.Product;
import com.aditya7812.products_service.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    private final CloudinaryService cloudinaryService;

    public ProductService(ProductRepository productRepo, CloudinaryService cloudinaryService) {
        this.productRepo = productRepo;
        this.cloudinaryService = cloudinaryService;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public ResponseEntity<Product> createProduct(ProductDTO dto, String userId) {
        try {
            String imageUrl = cloudinaryService.uploadImage(dto.getImage());
            Product product = new Product();
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setQuantity(dto.getQuantity());
            product.setCategory(dto.getCategory());
            product.setSellerId(userId);
            product.setImage_url(imageUrl);
            return ResponseEntity.ok(productRepo.save(product));
            // return ResponseEntity.ok("Product Created Successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving product");
        }
    }
    public List<Product> getSellerProducts(String sellerId) {
        return productRepo.findBySellerId(sellerId);
    }

    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    public Product addQuantity(AddQuantityDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(dto.getQuantity());
        return productRepo.save(product);
    }

    public Boolean checkStockAvailability(StockRequestDTO stockRequest) {
        System.out.println(stockRequest.getProducts().getFirst());
        List<String> productIds = stockRequest.getProducts().stream()
                .map(StockProduct::getProductId)
                .collect(Collectors.toList());
        List<Product> allp = productRepo.findAllProductListById(productIds);
        System.out.println(allp.size());

        Map<String, Integer> stockMap = productRepo.findAllProductListById(productIds)
            .stream()
            .collect(Collectors.toMap(Product::getId, Product::getQuantity));
        
        for (StockProduct product : stockRequest.getProducts()) {
            System.out.println(product.getProductId());
            int availableStock = stockMap.getOrDefault(product.getProductId(), 0);
            System.out.println(availableStock);
            if (availableStock < product.getQuantity()) {
                return false;
            }
        }

        return true;

    }

    @Transactional
    public void deductQuantity(String productId, int quantityToDeduct) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if (product.getQuantity() >= quantityToDeduct) {
                product.setQuantity(product.getQuantity() - quantityToDeduct);
                productRepo.save(product);
                System.out.println("✅ Quantity updated successfully for product ID: " + productId);
            } else {
                System.out.println("❌ Not enough stock for product ID: " + productId);
            }
        } else {
            System.out.println("❌ Product not found with ID: " + productId);
        }
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchByName(keyword);
    }

}
