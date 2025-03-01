package com.aditya7812.products_service.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public Product createProduct(ProductDTO dto, String userId) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(dto.getCategory());
        product.setSellerId(userId);
        return productRepo.save(product);
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

}
