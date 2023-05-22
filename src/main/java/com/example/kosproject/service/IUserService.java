package com.example.kosproject.service;

import com.example.kosproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User create(User user);
    List<User> findAll();
    Optional<User> findById(Integer id);
    void updateById(User user);
    void deleteById(Integer id);
}
