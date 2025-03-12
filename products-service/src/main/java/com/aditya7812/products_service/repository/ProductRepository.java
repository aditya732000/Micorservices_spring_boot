package com.aditya7812.products_service.repository;

import com.aditya7812.products_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findBySellerId(String sellerId);
    @Query("SELECT s FROM Product s WHERE s.id IN :productIds")
    List<Product> findAllProductListById(@Param("productIds") List<String> productIds);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByName(@Param("keyword") String keyword);
}
