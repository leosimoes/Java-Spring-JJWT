package com.app.jjwt.services;

import com.app.jjwt.dtos.LoginRequestDTO;
import com.app.jjwt.dtos.LoginResponseDTO;
import com.app.jjwt.dtos.RegisterRequestDTO;
import com.app.jjwt.dtos.RegisterResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);

}
