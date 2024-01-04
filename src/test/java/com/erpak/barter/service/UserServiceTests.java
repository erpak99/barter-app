package com.erpak.barter.service;

import com.erpak.barter.enums.Role;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.exceptions.UserNotFoundException;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach()
    public void init() {
        user =  User.builder()
                .id(1)
                .firstName("Arda")
                .lastName("Erpak")
                .email("test@hotmail.com")
                .birthYear(2000)
                .identityNumber("123456")
                .password("12345")
                .city("Ankara")
                .role(Role.USER)
                .build();
    }

    @Test
    public void testFindUserById() {

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.findById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1,result.getId())
        );

    }

    @Test
    public void testFindUserByIdNotFound() {

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> {
                    User user = userService.findById(1);
                },ExceptionMessages.USER_NOT_FOUND
        );

    }

    @Test
    public void testDeleteUserById() {

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        ResponseEntity<String> response = userService.deleteUser(1);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("User with email " + user.getEmail() + " deleted successfully",
                                                    response.getBody());

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteUserByIdNotFound() {

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> {
            userService.deleteUser(1);
        }, ExceptionMessages.USER_NOT_FOUND
        );


        verify(userRepository, never()).deleteById(1);


    }

}
