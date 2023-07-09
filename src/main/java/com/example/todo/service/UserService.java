package com.example.todo.service;

import com.example.todo.payload.UserDTO;
import com.example.todo.response.ApiResponse;

public interface UserService {
    ApiResponse registerUser(UserDTO userDTO);
}
