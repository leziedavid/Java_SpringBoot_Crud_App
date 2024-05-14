package com.mobisoft.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobisoft.crud.dto.TaskDTO;
import com.mobisoft.crud.entity.TaskEntity;
import com.mobisoft.crud.repository.TaskRepository;



@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDTO createTask(TaskDTO taskDTO) {
        try {
            TaskEntity taskEntity = convertToEntity(taskDTO);
            TaskEntity savedEntity = taskRepository.save(taskEntity);
            return convertToDTO(savedEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de la tâche: " + e.getMessage());
        }
    }

    public TaskDTO getTaskById(Integer taskId) {
        try {
            Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
            return optionalTaskEntity.map(this::convertToDTO).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la tâche par ID: " + e.getMessage());
        }
    }

    public List<TaskDTO> findAllTasks() {
        try {
            List<TaskEntity> taskEntities = taskRepository.findAllTasksNativeQuery();
            return taskEntities.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les tâches: " + e.getMessage());
        }
    }

    public List<TaskDTO> getAllTasks() {
        try {
            List<TaskEntity> taskEntities = taskRepository.findAll();
            return taskEntities.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de toutes les tâches: " + e.getMessage());
        }
    }

    public TaskDTO updateTask(Integer taskId, TaskDTO taskDTO) {
        try {
            Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);
            if (optionalTaskEntity.isPresent()) {
                TaskEntity taskEntity = optionalTaskEntity.get();
                updateEntityFromDTO(taskEntity, taskDTO);
                TaskEntity updatedEntity = taskRepository.save(taskEntity);
                return convertToDTO(updatedEntity);
            } else {
                throw new IllegalArgumentException("La tâche avec l'ID spécifié n'existe pas");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la mise à jour de la tâche: " + e.getMessage());
        }
    }

    public boolean deleteTask(Integer taskId) {
        try {
            if (taskRepository.existsById(taskId)) {
                taskRepository.deleteById(taskId);
                return true;
            } else {
                throw new IllegalArgumentException("La tâche avec l'ID spécifié n'existe pas");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la tâche: " + e.getMessage());
        }
    }

    private TaskDTO convertToDTO(TaskEntity taskEntity) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        taskDTO.setDescription(taskEntity.getDescription());
        taskDTO.setPriority(taskEntity.getPriority());
        taskDTO.setDuedate(taskEntity.getDuedate());
        taskDTO.setCompleted(taskEntity.isCompleted());
        taskDTO.setCreatedAt(taskEntity.getCreatedAt());
        taskDTO.setUpdatedAt(taskEntity.getUpdatedAt());
        taskDTO.setCompletedAt(taskEntity.getCompletedAt());
        return taskDTO;
    }

    private TaskEntity convertToEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        updateEntityFromDTO(taskEntity, taskDTO);
        return taskEntity;
    }

    private void updateEntityFromDTO(TaskEntity taskEntity, TaskDTO taskDTO) {
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setPriority(taskDTO.getPriority());
        taskEntity.setDuedate(taskDTO.getDuedate());
        taskEntity.setCompleted(taskDTO.isCompleted());
    }
}
