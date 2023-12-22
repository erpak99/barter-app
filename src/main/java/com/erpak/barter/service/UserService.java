package com.erpak.barter.service;

import com.erpak.barter.dto.ChangePasswordRequest;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request,
                               Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

       if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
           throw new IllegalStateException("Wrong password"); // update exception
       }
       if(!request.getNewPassword().equals(request.getConfirmationPassword())) {
           throw new IllegalStateException("Passwords are not same"); // update exception
       }

       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
       repository.save(user);
    }
}
