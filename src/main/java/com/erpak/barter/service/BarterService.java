package com.erpak.barter.service;

import com.erpak.barter.repository.BarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarterService {

    private final BarterRepository barterRepository;

}
