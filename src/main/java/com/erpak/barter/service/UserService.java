package com.erpak.barter.service;

import com.erpak.barter.dto.ChangePasswordRequest;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.exceptions.PasswordChangeException;
import com.erpak.barter.exceptions.UserNotFoundException;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public void changePassword(ChangePasswordRequest request,
                               Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

       if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
           throw new PasswordChangeException(ExceptionMessages.WRONG_PASSWORD);
       }
       if(!request.getNewPassword().equals(request.getConfirmationPassword())) {
           throw new PasswordChangeException(ExceptionMessages.PASSWORDS_NOT_SAME);
       }

       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
       repository.save(user);
    }

    public ResponseEntity<String> deleteUser(Integer id) {
        Optional<User> user = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND)));
        String userEmail = user.get().getEmail();
        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User with email " + userEmail + " deleted successfully");
    }

    public User findById(int id) {

        return repository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND)
        );
    }
}
