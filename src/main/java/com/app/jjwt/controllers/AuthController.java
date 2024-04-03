package com.app.jjwt.controllers;

import com.app.jjwt.dtos.LoginRequestDTO;
import com.app.jjwt.dtos.LoginResponseDTO;
import com.app.jjwt.dtos.RegisterRequestDTO;
import com.app.jjwt.dtos.RegisterResponseDTO;
import com.app.jjwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = this.authService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        RegisterResponseDTO registerResponseDTO = this.authService.register(registerRequestDTO);
        return ResponseEntity.ok(registerResponseDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<String> authenticatedUsers(){
        String msg = "User has been authenticated and has the role USER";
       return ResponseEntity.ok(msg);
    }

    @GetMapping("/admins")
    public ResponseEntity<String> authenticatedAdmins(){
        String msg = "User has been authenticated and has the role ADMIN";
        return ResponseEntity.ok(msg);
    }

}
