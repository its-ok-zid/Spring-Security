package com.zidtech.security.controller;

import com.zidtech.security.DTO.LoginRequestDTO;
import com.zidtech.security.DTO.LoginResponseDTO;
import com.zidtech.security.DTO.SignUpRequestDTO;
import com.zidtech.security.DTO.SignUpResponseDTO;
import com.zidtech.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {

        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signup(SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(authService.signup(signUpRequestDTO));
    }
}
