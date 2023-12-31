package com.erpak.barter.service;

import com.erpak.barter.dto.BarterCreateRequest;
import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.model.Barter;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.BarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarterService {

    private final BarterRepository barterRepository;
    private final UserService userService;
    private final ProductService productService;

    public ResponseEntity<String> createBarterRequest(BarterCreateRequest barterCreateRequest) {

        User userOne = userService.findById(barterCreateRequest.getUserOneId());
        Product productOne = productService.findById(barterCreateRequest.getProductOneId());

        User userTwo = userService.findById(barterCreateRequest.getUserTwoId());
        Product productTwo = productService.findById(barterCreateRequest.getProductTwoId());

        Barter barter = Barter.builder()
                .userOne(userOne)
                .productOne(productOne)
                .userTwo(userTwo)
                .productTwo(productTwo)
                .barterStatus(BarterStatus.PENDING)
                .build();

        barterRepository.save(barter);
        return ResponseEntity.status(HttpStatus.CREATED)
                                        .body("Barter request created successfully");

    }

    public Barter findById(int id) {
        return barterRepository.findById(id).orElseThrow();
    }
}
