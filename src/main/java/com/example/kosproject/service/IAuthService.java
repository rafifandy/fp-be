package com.example.kosproject.service;

import com.example.kosproject.model.entity.Auth;
import com.example.kosproject.model.request.LoginRequest;
import com.example.kosproject.model.request.RegistrationRequest;

public interface IAuthService {

    Auth findByEmail(String email);

    Auth save(Auth auth);
    String register(RegistrationRequest registrationRequest);

    String adminRegister(RegistrationRequest registrationRequest);
    String login(LoginRequest loginRequest);
}
