package com.mobisoft.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mobisoft.crud.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

// Ajoutez des méthodes personnalisées de requête si nécessaire
    @Query(value = "SELECT * FROM Tasks", nativeQuery = true)
    List<TaskEntity> findAllTasksNativeQuery();

}
