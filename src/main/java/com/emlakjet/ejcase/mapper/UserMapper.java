package com.emlakjet.ejcase.mapper;

import com.emlakjet.ejcase.entities.User;
import com.emlakjet.ejcase.model.user.UserRequest;
import com.emlakjet.ejcase.model.user.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User userRequestToUser(UserRequest userRequest);

    UserRequest userToUserRequest(User user);

    UserResponse userToUserResponse(User user);

    User userResponseToUser(UserResponse userResponse);

    List<UserResponse> toResponseList(List<User> userList);
}