package com.erpak.barter.controller;

import com.erpak.barter.dto.BarterCreateRequest;
import com.erpak.barter.dto.BarterDTO;
import com.erpak.barter.enums.BarterStatus;
import com.erpak.barter.model.Barter;
import com.erpak.barter.service.BarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{barterId}/approve")
    public ResponseEntity<String> approveBarter(@PathVariable int barterId) {
        return barterService.approveBarter(barterId);

    }

    @PostMapping("/{barterId}/reject")
    public ResponseEntity<String> rejectBarter(@PathVariable int barterId) {
        return barterService.approveBarter(barterId);

    }

    @GetMapping("/byUserId/{userId}")
    public List<BarterDTO> getBartersByUserId(@PathVariable int userId) throws Exception {
        return barterService.getBartersByUserId(userId);
    }

    @GetMapping("/byUserIdAndBarterStatus")
    public List<BarterDTO> findBartersByUserIdAndBarterStatus(
            @RequestParam int userId, @RequestParam BarterStatus status) {

        return barterService.findBartersByUserIdAndBarterStatus(userId,status);

    }


}
