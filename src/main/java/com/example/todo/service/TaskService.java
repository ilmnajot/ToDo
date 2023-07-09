package com.example.todo.service;


import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import com.example.todo.response.ApiResponse;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();

    List<Task> findAllCompletedTask();

    List<Task> findAllUncompledTask();

    Task createNewTask(Task task, User user);

    ApiResponse updateTask(Task task, Long id);

    ApiResponse deleteTask(Long id);

    ApiResponse completedTask(Long id);
}
