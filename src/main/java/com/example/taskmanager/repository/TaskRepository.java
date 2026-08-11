package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.Priorite;
import com.example.taskmanager.enums.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitreContainingIgnoreCase(String titre);

    List<Task> findByStatut(Statut statut);

    List<Task> findByPriorite(Priorite priorite);

    List<Task> findByUtilisateurId(Long utilisateurId);

    List<Task> findByStatutAndPriorite(Statut statut, Priorite priorite);

    List<Task> findByStatutAndUtilisateurId(Statut statut, Long utilisateurId);

    List<Task> findByPrioriteAndUtilisateurId(Priorite priorite, Long utilisateurId);

    List<Task> findByStatutAndPrioriteAndUtilisateurId(
            Statut statut,
            Priorite priorite,
            Long utilisateurId
    );
}