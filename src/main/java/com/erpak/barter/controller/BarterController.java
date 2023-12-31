package com.erpak.barter.controller;

import com.erpak.barter.dto.BarterCreateRequest;
import com.erpak.barter.dto.BarterDTO;
import com.erpak.barter.model.Barter;
import com.erpak.barter.service.BarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/barters")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createBarterRequest(
                                @RequestBody BarterCreateRequest barterCreateRequest) {

        return barterService.createBarterRequest(barterCreateRequest);
    }

    @GetMapping("/{id}")
    public BarterDTO findById(@PathVariable int id) {
        Barter barter = barterService.findById(id);
        return new BarterDTO(barter);
    }

}
