package com.aditya7812.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya7812.user.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    
}
