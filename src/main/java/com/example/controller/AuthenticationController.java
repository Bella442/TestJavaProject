package com.example.controller;

import com.example.dto.LoginResponseDto;
import com.example.dto.LoginUserDto;
import com.example.dto.RegisterUserDto;
import com.example.dto.RegisterUserResponseDto;
import com.example.entity.User;
import com.example.service.AuthenticationService;
import com.example.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponseDto> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        RegisterUserResponseDto registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponseDto);
    }
}
