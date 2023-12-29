package com.erpak.barter.controller;

import com.erpak.barter.service.BarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/barter")
@RequiredArgsConstructor
public class BarterController {

    private final BarterService barterService;

}
