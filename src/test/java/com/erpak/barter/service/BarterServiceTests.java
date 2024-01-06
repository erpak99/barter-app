package com.erpak.barter.service;

import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.exceptions.BarterNotFoundException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Barter;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.BarterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BarterServiceTests {

    @InjectMocks
    private BarterService barterService;

    @Mock
    private BarterRepository barterRepository;

    private Barter barter;
    private User userOne;
    private Product productOne;
    private User userTwo;
    private Product productTwo;

    @BeforeEach
    public void init() {
        userOne = User.builder().id(1).build();
        productOne = Product.builder().id(1).build();
        userTwo = User.builder().id(2).build();
        productTwo = Product.builder().id(2).build();
        barter = Barter.builder()
                .id(1)
                .userOne(userOne)
                .productOne(productOne)
                .userTwo(userTwo)
                .productTwo(productTwo)
                .barterStatus(BarterStatus.PENDING)
                .build();
    }

    @Test
    public void testFindBarterById() {

        when(barterRepository.findById(1)).thenReturn(Optional.of(barter));

        Barter result = barterService.findById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1,result.getId())
        );

    }

    @Test
    public void testFindBarterByIdNotFound() {

        when(barterRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(
                BarterNotFoundException.class,
                () -> {
                    Barter barter = barterService.findById(1);
                },
                ExceptionMessages.BARTER_NOT_FOUND
        );

    }


}
