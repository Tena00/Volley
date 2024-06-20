package com.manuel.tfg.services.impl;


import com.manuel.tfg.services.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${login.password}")
    private String correctPassword;

    public boolean authenticate(String password) {
        return correctPassword.equals(password);
    }
}
