package com.app.jjwt.services;

import com.app.jjwt.dtos.LoginRequestDTO;
import com.app.jjwt.dtos.LoginResponseDTO;
import com.app.jjwt.dtos.RegisterRequestDTO;
import com.app.jjwt.dtos.RegisterResponseDTO;
import com.app.jjwt.entities.JJWTUser;
import com.app.jjwt.entities.Role;
import com.app.jjwt.repositories.JJWTUserRepository;
import com.app.jjwt.security.JJWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private PasswordEncoder passwordEncoder;

    private JJWTUserRepository jjwtUserRepository;

    private JJWTTokenService jjwtTokenService;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder,
                           JJWTUserRepository jjwtUserRepository,
                           JJWTTokenService jjwtTokenService
                           ){
        this.passwordEncoder = passwordEncoder;
        this.jjwtUserRepository = jjwtUserRepository;
        this.jjwtTokenService = jjwtTokenService;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Optional<JJWTUser> jjwtUserOptional = jjwtUserRepository.findByLogin(loginRequestDTO.login());
        if(jjwtUserOptional.isPresent()){
            JJWTUser jjwtUser = jjwtUserOptional.get();
            if(passwordEncoder.matches(loginRequestDTO.password(), jjwtUser.getPassword())){
                String name = jjwtUser.getName();
                String token = this.jjwtTokenService.generateToken(jjwtUser);
                return new LoginResponseDTO(name, token);
            }
        }

        return null;
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO){
        Optional<JJWTUser> jjwtUserOptional = jjwtUserRepository.findByLogin(registerRequestDTO.login());
        if(jjwtUserOptional.isEmpty()){
            JJWTUser jjwtUser = new JJWTUser();
            jjwtUser.setLogin(registerRequestDTO.login());
            jjwtUser.setPassword(registerRequestDTO.password());
            jjwtUser.setName(registerRequestDTO.name());
            jjwtUser.setRoles(Collections.unmodifiableSet(Set.of(Role.ROLE_USER)));

            this.jjwtUserRepository.save(jjwtUser);

            String token = this.jjwtTokenService.generateToken(jjwtUser);
            return new RegisterResponseDTO(jjwtUser.getName(), token);
        }

        return null;
    }
}
