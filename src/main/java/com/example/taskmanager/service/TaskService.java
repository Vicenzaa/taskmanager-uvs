package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Priorite;
import com.example.taskmanager.enums.Statut;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Créer une tâche et l'affecter à un utilisateur
    public Task createTask(Task task, Long utilisateurId) {
        User user = userRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + utilisateurId));

        task.setUtilisateur(user);
        if (task.getStatut() == null) {
            task.setStatut(Statut.A_FAIRE);
        }

        if (task.getPriorite() == null) {
            task.setPriorite(Priorite.MOYENNE);
        }

        return taskRepository.save(task);
    }

    // Afficher toutes les tâches
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Afficher une tâche par ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche non trouvée avec l'id: " + id));
    }

    // Modifier une tâche
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);

        task.setTitre(taskDetails.getTitre());
        task.setDescription(taskDetails.getDescription());
        task.setDateLimite(taskDetails.getDateLimite());
        task.setPriorite(taskDetails.getPriorite());
        task.setStatut(taskDetails.getStatut());

        return taskRepository.save(task);
    }

    // Supprimer une tâche
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    // Modifier le statut d'une tâche
    public Task updateTaskStatus(Long id, Statut statut) {
        Task task = getTaskById(id);
        task.setStatut(statut);
        return taskRepository.save(task);
    }

    // Rechercher une tâche par titre
    public List<Task> searchByTitle(String titre) {
        return taskRepository.findByTitreContainingIgnoreCase(titre);
    }

    // Filtrer les tâches par statut
    public List<Task> filterByStatut(Statut statut) {
        return taskRepository.findByStatut(statut);
    }

    // Filtrer les tâches par priorité
    public List<Task> filterByPriorite(Priorite priorite) {
        return taskRepository.findByPriorite(priorite);
    }

    // Filtrer les tâches par utilisateur
    public List<Task> filterByUtilisateur(Long utilisateurId) {
        return taskRepository.findByUtilisateurId(utilisateurId);
    }

    // Filtrer par statut ET priorité
    public List<Task> filterByStatutAndPriorite(Statut statut, Priorite priorite) {
        return taskRepository.findByStatutAndPriorite(statut, priorite);
    }

    // Filtrer par statut, priorité ET utilisateur
    public List<Task> filterByStatutAndPrioriteAndUtilisateur(Statut statut, Priorite priorite, Long utilisateurId) {
        return taskRepository.findByStatutAndPrioriteAndUtilisateurId(statut, priorite, utilisateurId);
    }
}