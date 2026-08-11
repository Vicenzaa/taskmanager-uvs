package com.example.taskmanager.config;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Priorite;
import com.example.taskmanager.enums.Statut;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            // 1. Créer deux utilisateurs
            User user1 = new User("Dupont", "Jean", "jean.dupont@email.com");
            User user2 = new User("Martin", "Sophie", "sophie.martin@email.com");
            
            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);

            System.out.println("Utilisateurs créés : " + user1.getId() + ", " + user2.getId());

            // 2. Créer quelques tâches
            Task task1 = new Task();
            task1.setTitre("Développer le module utilisateur");
            task1.setDescription("Créer les endpoints REST pour les utilisateurs");
            task1.setDateLimite(LocalDate.now().plusDays(7));
            task1.setPriorite(Priorite.HAUTE);
            task1.setStatut(Statut.EN_COURS);
            task1.setUtilisateur(user1);

            Task task2 = new Task();
            task2.setTitre("Configurer Swagger");
            task2.setDescription("Ajouter Springdoc OpenAPI");
            task2.setDateLimite(LocalDate.now().plusDays(3));
            task2.setPriorite(Priorite.MOYENNE);
            task2.setStatut(Statut.A_FAIRE);
            task2.setUtilisateur(user2);

            Task task3 = new Task();
            task3.setTitre("Écrire le README");
            task3.setDescription("Documenter le projet sur GitHub");
            task3.setDateLimite(LocalDate.now().plusDays(1));
            task3.setPriorite(Priorite.BASSE);
            task3.setStatut(Statut.TERMINEE);
            task3.setUtilisateur(user1);

            taskRepository.save(task1);
            taskRepository.save(task2);
            taskRepository.save(task3);

            System.out.println("Données de test insérées avec succès !");
        };
    }
}
