package com.emlakjet.ejcase.service;


import com.emlakjet.ejcase.entities.User;
import com.emlakjet.ejcase.mapper.UserMapper;
import com.emlakjet.ejcase.model.user.UserRequest;
import com.emlakjet.ejcase.model.user.UserResponse;
import com.emlakjet.ejcase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User existingUser = userRepository.findByEmail(userRequest.getEmail());

        if (existingUser != null) {
            return null; // E-posta adresi zaten kullanılıyor
        }

        User savedUser = userMapper.userRequestToUser(userRequest);
        userRepository.save(savedUser);

        return userMapper.userToUserResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return userMapper.toResponseList(users);
    }
}
