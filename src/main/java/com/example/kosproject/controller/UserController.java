package com.example.kosproject.controller;

import com.example.kosproject.model.entity.User;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody @Valid User user) throws Exception {
        User newUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add tenant", user));
    }

    @GetMapping
    public ResponseEntity getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get all user", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Integer id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success get by id", user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete by id", id));
    }

    @PutMapping("/update")
    public ResponseEntity updateById(@RequestBody User user) {
        userService.updateById(user);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success update by id", user));
    }
}
