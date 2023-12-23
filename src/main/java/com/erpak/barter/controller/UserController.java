package com.erpak.barter.controller;

import com.erpak.barter.dto.ChangePasswordRequest;
import com.erpak.barter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
                            @RequestBody @Valid ChangePasswordRequest request,
                            Principal connectedUser)  {

        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
