package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Priorite;
import com.example.taskmanager.enums.Statut;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task, Long utilisateurId) {
        User user = userRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + utilisateurId));

        if (task.getTitre() == null || task.getTitre().trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre de la tâche est obligatoire");
        }

        task.setUtilisateur(user);

        if (task.getStatut() == null) {
            task.setStatut(Statut.A_FAIRE);
        }

        if (task.getPriorite() == null) {
            task.setPriorite(Priorite.MOYENNE);
        }

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche non trouvée avec l'id: " + id));
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);

        if (taskDetails.getTitre() != null && !taskDetails.getTitre().trim().isEmpty()) {
            task.setTitre(taskDetails.getTitre());
        }

        task.setDescription(taskDetails.getDescription());
        task.setDateLimite(taskDetails.getDateLimite());

        if (taskDetails.getPriorite() != null) {
            task.setPriorite(taskDetails.getPriorite());
        }

        if (taskDetails.getStatut() != null) {
            task.setStatut(taskDetails.getStatut());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public Task updateTaskStatus(Long id, Statut statut) {
        Task task = getTaskById(id);

        if (statut == null) {
            throw new IllegalArgumentException("Le statut ne peut pas être nul");
        }

        task.setStatut(statut);
        return taskRepository.save(task);
    }

    public List<Task> searchByTitle(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre de recherche ne peut pas être vide");
        }
        return taskRepository.findByTitreContainingIgnoreCase(titre);
    }

    public List<Task> filterByStatut(Statut statut) {
        return taskRepository.findByStatut(statut);
    }

    public List<Task> filterByPriorite(Priorite priorite) {
        return taskRepository.findByPriorite(priorite);
    }

    public List<Task> filterByUtilisateur(Long utilisateurId) {
        return taskRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Task> filterByStatutAndPriorite(Statut statut, Priorite priorite) {
        return taskRepository.findByStatutAndPriorite(statut, priorite);
    }

    public List<Task> filterByStatutAndPrioriteAndUtilisateur(Statut statut, Priorite priorite, Long utilisateurId) {
        return taskRepository.findByStatutAndPrioriteAndUtilisateurId(statut, priorite, utilisateurId);
    }
}