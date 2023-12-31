package com.emlakjet.ejcase.controller;


import com.emlakjet.ejcase.model.user.UserRequest;
import com.emlakjet.ejcase.model.user.UserResponse;
import com.emlakjet.ejcase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserResponse createdUser = userService.createUser(userRequest);
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aynı e-posta adresiyle kayıtlı kullanıcı mevcut.");
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long userId, @RequestBody UserRequest userRequest){
        userService.updateUser(userId, userRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}


