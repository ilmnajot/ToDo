package com.example.todo.repository;


import com.example.todo.entity.Task;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTask(String task);
    List<Task> findByCompletedTrue(Sort sort);
    List<Task> findByCompletedFalse(Sort sort);
    List<Task> findAll(Sort sort);
    Task getById(Long id);

}
