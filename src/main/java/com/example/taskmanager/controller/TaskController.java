package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.Priorite;
import com.example.taskmanager.enums.Statut;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Créer une tâche et l'affecter à un utilisateur
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task,
                                           @RequestParam Long utilisateurId) {
        Task createdTask = taskService.createTask(task, utilisateurId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Afficher toutes les tâches
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Afficher une tâche par ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    // Modifier une tâche
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @Valid @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Modifier le statut d'une tâche
    @PatchMapping("/{id}/statut")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id,
                                                  @RequestParam Statut statut) {
        Task updatedTask = taskService.updateTaskStatus(id, statut);
        return ResponseEntity.ok(updatedTask);
    }

    // Rechercher une tâche par titre
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchByTitle(@RequestParam String titre) {
        List<Task> tasks = taskService.searchByTitle(titre);
        return ResponseEntity.ok(tasks);
    }

    // Filtrer les tâches (statut, priorité, utilisateur)
    @GetMapping("/filter")
    public ResponseEntity<List<Task>> filterTasks(
            @RequestParam(required = false) Statut statut,
            @RequestParam(required = false) Priorite priorite,
            @RequestParam(required = false) Long utilisateurId) {

        List<Task> tasks;

        if (statut != null && priorite != null && utilisateurId != null) {
            tasks = taskService.filterByStatutAndPrioriteAndUtilisateur(statut, priorite, utilisateurId);
        } else if (statut != null && priorite != null) {
            tasks = taskService.filterByStatutAndPriorite(statut, priorite);
        } else if (statut != null) {
            tasks = taskService.filterByStatut(statut);
        } else if (priorite != null) {
            tasks = taskService.filterByPriorite(priorite);
        } else if (utilisateurId != null) {
            tasks = taskService.filterByUtilisateur(utilisateurId);
        } else {
            tasks = taskService.getAllTasks();
        }

        return ResponseEntity.ok(tasks);
    }
}