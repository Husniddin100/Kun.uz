package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(authService.auth(authDTO));
    }
    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }
    @PostMapping("/registrationSms")
    public ResponseEntity<Boolean> registrationSms(@RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(authService.registrationSms(dto));
    }
    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }


}
