package com.emlakjet.ejcase.service;



import com.emlakjet.ejcase.entities.User;
import com.emlakjet.ejcase.entities.UserRequest;
import com.emlakjet.ejcase.entities.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
}
