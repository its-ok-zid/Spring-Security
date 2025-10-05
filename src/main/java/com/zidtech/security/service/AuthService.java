package com.zidtech.security.service;

import com.zidtech.security.DTO.LoginRequestDTO;
import com.zidtech.security.DTO.LoginResponseDTO;
import com.zidtech.security.DTO.SignUpRequestDTO;
import com.zidtech.security.DTO.SignUpResponseDTO;
import com.zidtech.security.entity.User;
import com.zidtech.security.repository.UserRepository;
import com.zidtech.security.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getUser_uuid());
    }

    public SignUpResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        User user = userRepository.findByUsername(signUpRequestDTO.getUsername()).orElse(null);

        if (user != null) throw new RuntimeException("User already exists");

        user = userRepository.save(
                User
                        .builder()
                        .username(signUpRequestDTO.getUsername())
                        .email(signUpRequestDTO.getEmail())
                        .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                        .build());

        return new SignUpResponseDTO(user.getUser_uuid(), user.getUsername());
    }
}
