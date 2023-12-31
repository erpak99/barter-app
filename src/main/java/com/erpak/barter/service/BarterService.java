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

import java.util.Optional;

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

    public ResponseEntity<String> approveBarter(int barterId) {

        Optional<Barter> optionalBarter = barterRepository.findById(barterId);

        if (optionalBarter.isPresent()) {
            Barter barter = optionalBarter.get();

            if (barter.getBarterStatus() == BarterStatus.PENDING) {
                barter.setBarterStatus(BarterStatus.APPROVED);
                barterRepository.save(barter);
                return ResponseEntity.ok("Barter request approved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Barter request is not in a pending state");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Barter request not found");
        }

        }


    public ResponseEntity<String> rejectBarter(int barterId) {

        Optional<Barter> optionalBarter = barterRepository.findById(barterId);

        if (optionalBarter.isPresent()) {
            Barter barter = optionalBarter.get();

            if (barter.getBarterStatus() == BarterStatus.PENDING) {
                barter.setBarterStatus(BarterStatus.REJECTED);
                barterRepository.save(barter);
                return ResponseEntity.ok("Barter request rejected successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Barter request is not in a pending state");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Barter request not found");
        }

    }

    }
