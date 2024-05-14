package com.mobisoft.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobisoft.crud.dto.TaskDTO;
import com.mobisoft.crud.service.TaskService;

@RestController

public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/api/v1/createTask")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }

    @GetMapping("/api/v1/getTaskById/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @GetMapping("/api/v1/getAllTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/api/v1/findAllTasks")
    public ResponseEntity<List<TaskDTO>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @PutMapping("/api/v1/updateTask/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Integer taskId, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDTO));
    }

    @DeleteMapping("/api/v1/deleteTask/{taskId}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.deleteTask(taskId));
    }

}
