package com.example.kosproject.service;

import com.example.kosproject.exception.EntityExistException;
import com.example.kosproject.exception.NotFoundException;
import com.example.kosproject.exception.UnauthorizedException;
import com.example.kosproject.model.entity.Auth;
import com.example.kosproject.model.entity.User;
import com.example.kosproject.model.request.LoginRequest;
import com.example.kosproject.model.request.RegistrationRequest;
import com.example.kosproject.repository.AuthRepository;
import com.example.kosproject.util.JwtUtil;
import com.example.kosproject.util.Role;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{
    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Auth findByEmail(String email) {
        return authRepository.findByEmail(email).orElse(null);
    }
    @Override
    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }
    @Transactional
    @Override
    public String register(RegistrationRequest registrationRequest) {
        try {
            Auth auth = modelMapper.map(registrationRequest, Auth.class);
            auth.setRole(Role.TENANT);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registrationRequest, User.class);
            user.setAuth(authResult);
            userService.updateById(user);
            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
    @Transactional
    @Override
    public String adminRegister(RegistrationRequest registrationRequest) {
        try {
            Auth auth = modelMapper.map(registrationRequest, Auth.class);
            auth.setActive(true);
            auth.setRole(Role.ADMIN);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registrationRequest, User.class);
            user.setAuth(authResult);
            userService.updateById(user);

            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Transactional
    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new NotFoundException();
            if (!auth.get().isActive()) {
                throw new RuntimeException("Account not verified");
            }
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new UnauthorizedException("Password wrong");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
