package com.gulbi.Backend.domain.user.controller;


import com.gulbi.Backend.domain.user.dto.LoginRequestDto;
import com.gulbi.Backend.domain.user.dto.RegisterRequestDto;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequestDto request) {
        userService.register(request);
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setEmail(request.getEmail());
        loginRequest.setPassword(request.getPassword());
        String token = userService.login(loginRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "User registered and logged in successfully");


        return ResponseEntity.ok(response);
    }




    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDto request) {
        String token = userService.login(request);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
