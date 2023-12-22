package com.erpak.barter.controller;

import com.erpak.barter.dto.AuthenticationRequest;
import com.erpak.barter.dto.AuthenticationResponse;
import com.erpak.barter.dto.RegisterRequest;
import com.erpak.barter.exceptions.MernisValidationException;
import com.erpak.barter.mernis.VFEKPSPublicSoap;
import com.erpak.barter.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws Exception {

            VFEKPSPublicSoap publicSoap = new VFEKPSPublicSoap();
            boolean isRealPerson = publicSoap.TCKimlikNoDogrula(
                    Long.valueOf(request.getIdentityNumber()),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getBirthYear()
            );

            if (isRealPerson) {
                service.register(request);
                return ResponseEntity.ok("Registration successful");
            } else {
                throw new MernisValidationException("MERNIS validation failed" +
                                                    " Please review your security credentials");
            }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

}
