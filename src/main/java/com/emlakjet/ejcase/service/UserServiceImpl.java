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
            return null;
        }

        User savedUser = userMapper.userRequestToUser(userRequest);
        userRepository.save(savedUser);

        return userMapper.userToUserResponse(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("böyle bir user mevcut değil.");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı mevcut değil."));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        userRepository.save(user);
        return userMapper.userToUserResponse(user);


    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return userMapper.toResponseList(users);
    }

}
