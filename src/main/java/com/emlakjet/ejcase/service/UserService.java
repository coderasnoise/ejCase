package com.emlakjet.ejcase.service;


import com.emlakjet.ejcase.model.user.UserRequest;
import com.emlakjet.ejcase.model.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

}
