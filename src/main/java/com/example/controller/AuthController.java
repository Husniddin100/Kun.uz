package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.dto.profileDTO.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.enums.LangEnum;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    private Logger log = LoggerFactory.getLogger(AuthController.class);

    @Operation(summary = "Api for login", description = "this api used for authorization")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody AuthDTO auth, @RequestHeader(value = "Accept-Language", defaultValue = "ru") LangEnum language) {
        log.info("Login {} ", auth.getEmail());
        return ResponseEntity.ok(authService.auth(auth,language));
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> registrationEmail(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/registrationSms")
    public ResponseEntity<Boolean> registratioPhone(@RequestBody RegistrationDTO dto) {
        log.info("registration {}", dto.getEmail());
        return ResponseEntity.ok(authService.registrationPone(dto));
    }

    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        log.warn("registration {}", jwt);
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}
