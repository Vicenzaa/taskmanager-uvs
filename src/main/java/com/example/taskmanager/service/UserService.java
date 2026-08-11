package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Créer un utilisateur
    public User createUser(User user) {
        // Vérifier que l'email n'existe pas déjà
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }
        return userRepository.save(user);
    }

    // Consulter tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Consulter un utilisateur par ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + id));
    }
}