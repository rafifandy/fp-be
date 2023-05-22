package com.example.kosproject.repository;

import com.example.kosproject.model.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    public Optional<Auth> findByEmail(String email);
}
