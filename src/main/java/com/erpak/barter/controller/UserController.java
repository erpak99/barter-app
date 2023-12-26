package com.erpak.barter.controller;

import com.erpak.barter.dto.ChangePasswordRequest;
import com.erpak.barter.dto.UserDto;
import com.erpak.barter.model.User;
import com.erpak.barter.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        User user = service.findById(id);
        return new UserDto(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return service.deleteUser(id);
    }

}
