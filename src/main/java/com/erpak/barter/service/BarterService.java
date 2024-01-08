package com.erpak.barter.service;

import com.erpak.barter.dto.BarterCreateRequest;
import com.erpak.barter.dto.BarterDTO;
import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.exceptions.BarterNotFoundException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Barter;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.BarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarterService {

    private final BarterRepository barterRepository;
    private final UserService userService;
    private final ProductService productService;
    private final EmailSenderService emailSenderService;

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

        sendEmail(barterCreateRequest);

        barterRepository.save(barter);
        return ResponseEntity.status(HttpStatus.CREATED)
                                        .body("Barter request created successfully");

    }

    private void sendEmail(BarterCreateRequest barterCreateRequest) {

        User userToCreateBarterRequest = userService.findById(barterCreateRequest.getUserOneId());
        User userToSendEmail = userService.findById(barterCreateRequest.getUserTwoId());
        Product productForUserOne = productService.findById(barterCreateRequest.getProductOneId());
        Product productForUserTwo = productService.findById(barterCreateRequest.getProductTwoId());


        String body = "You have a new barter request from user "
                + userToCreateBarterRequest.getEmail() + " for your product " + productForUserTwo.getName() +
                " with the product " + productForUserOne.getName();

        String subject = "New Barter Request!";

        emailSenderService.sendSimpleEmail(userToSendEmail.getEmail(),body,subject);

    }

    public Barter findById(int id) {

        return barterRepository.findById(id).orElseThrow(
                () -> new BarterNotFoundException(ExceptionMessages.BARTER_NOT_FOUND)
                );
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

    public List<BarterDTO> getBartersByUserId(int userId) throws Exception {

        List<BarterDTO> barterList = barterRepository
                .findByUserOneIdOrUserTwoId(userId,userId)
                .stream()
                .map(barter -> new BarterDTO(barter))
                .toList();
        if(!barterList.isEmpty()) {
            return barterList;
        }
        throw new BarterNotFoundException(ExceptionMessages.BARTER_NOT_FOUND);
    }

    public List<BarterDTO> findBartersByUserIdAndBarterStatus(int userId, BarterStatus status) {

        List<BarterDTO> barterList = barterRepository
                .findByUserOneIdAndBarterStatusOrUserTwoIdAndBarterStatus
                (userId, status, userId, status)
                .stream()
                .map(barter -> new BarterDTO(barter))
                .toList();
        if(!barterList.isEmpty()) {
            return barterList;
        }
        throw new BarterNotFoundException(ExceptionMessages.BARTER_NOT_FOUND);
    }
}
