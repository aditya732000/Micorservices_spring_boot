package com.aditya7812.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya7812.user.dto.LoginRequest;
import com.aditya7812.user.dto.LoginResponse;
import com.aditya7812.user.dto.RegisterRequest;
import com.aditya7812.user.model.Buyer;
import com.aditya7812.user.model.Seller;
import com.aditya7812.user.model.User;
import com.aditya7812.user.repository.BuyerRepository;
import com.aditya7812.user.repository.SellerRepository;
import com.aditya7812.user.repository.UserRepository;
import com.aditya7812.user.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final BuyerRepository buyerRepository;

    private final SellerRepository sellerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    
    public LoginResponse login(LoginRequest login) throws Exception {
        try {
            authenticate(login.getEmail(), login.getPassword());

            User user = userRepository.findByEmail(login.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String token = jwtUtil.generateToken(user);
            return new LoginResponse(token);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BadCredentialsException("Incorrect username or password");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    
    @Transactional
    public LoginResponse register(RegisterRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }
        try {
            User user = createUserFromRegistration(request);
            String token = jwtUtil.generateToken(user);
            return new LoginResponse(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private User createUserFromRegistration(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        User savedUser = userRepository.save(user);

        if (request.getRole() == "BUYER") {
            Buyer buyer = new Buyer();
            buyer.setName(request.getName());
            buyer.setEmail(request.getEmail());
            buyer.setUserId(savedUser.getId());
            buyerRepository.save(buyer);
        } else {
            Seller seller = new Seller();
            seller.setShopName(request.getShopName());
            seller.setEmail(request.getEmail());
            seller.setPhoneNumber(request.getPhoneNumber());
            seller.setLocation(request.getLocation());
            seller.setZipCode(request.getZipCode());
            seller.setUserId(savedUser.getId());
            sellerRepository.save(seller);
        }

        return savedUser;
    }
}