package com.example.todo.controller;

import com.example.todo.entity.User;
import com.example.todo.payload.LoginDTO;
import com.example.todo.payload.UserDTO;
import com.example.todo.response.ApiResponse;
import com.example.todo.security.JwtProvider;
import com.example.todo.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;


    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;

        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse = authService.registerUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO){
    try {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        ));

        User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }catch (Exception e){
        return null;
    }
    }

}