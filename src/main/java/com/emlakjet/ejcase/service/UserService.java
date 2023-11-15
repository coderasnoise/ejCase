package com.emlakjet.ejcase.service;


import com.emlakjet.ejcase.entities.User;
import com.emlakjet.ejcase.model.user.UserRequest;
import com.emlakjet.ejcase.model.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    void deleteUser(Long userId);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    List<UserResponse> getAllUsers();


}
