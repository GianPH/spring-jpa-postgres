package com.gmail.gian.springjpapostgres.controller;

import com.gmail.gian.springjpapostgres.model.Task;
import com.gmail.gian.springjpapostgres.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by GianPH - 5/5/2018
 */

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping(value = {"", "list"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> findById(@PathVariable(name = "id") String id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found"));
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {

        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    //    Hard Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") String id) {

        Task task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task Not Found"));

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PutMapping(value = "update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
}
