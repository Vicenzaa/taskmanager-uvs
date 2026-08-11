# TaskManager

TaskManager est une application REST développée avec **Spring Boot** permettant à une petite entreprise de gérer ses utilisateurs et leurs tâches quotidiennes.

L'application permet notamment de créer et consulter des utilisateurs, de créer et gérer des tâches, de les affecter à des utilisateurs, de modifier leur statut et leur priorité, ainsi que de rechercher et filtrer les tâches.

> Projet réalisé dans le cadre d'un **examen de rattrapage**.

---

## Table des matières

- [Présentation](#-présentation)
- [Objectif](#-objectif)
- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Architecture du projet](#-architecture-du-projet)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Lancement de l'application](#-lancement-de-lapplication)
- [URLs importantes](#-urls-importantes)
- [Base de données](#-base-de-données)
- [Données de test](#-données-de-test)
- [Routes API](#-routes-api)
- [Recherche et filtres](#-recherche-et-filtres)
- [Gestion des erreurs](#-gestion-des-erreurs)
- [Validation des données](#-validation-des-données)
- [Documentation Swagger](#-documentation-swagger)
- [Démonstration](#-démonstration)
- [Commandes utiles](#-commandes-utiles)
- [Auteur](#-auteur)

---

## Présentation

TaskManager est une **API REST** développée avec Spring Boot.

Elle permet de gérer :

- les utilisateurs ;
- les tâches ;
- l'affectation des tâches aux utilisateurs ;
- le statut des tâches ;
- la priorité des tâches ;
- la recherche de tâches ;
- le filtrage des tâches.

---

## Objectif

L'objectif du projet est de fournir une solution simple permettant à une petite entreprise **d'enregistrer, d'organiser et de suivre les tâches de ses employés**.

---

## Fonctionnalités

### Gestion des utilisateurs

- Créer un utilisateur.
- Consulter la liste des utilisateurs.
- Consulter un utilisateur par son identifiant.
- Garantir l'unicité de l'adresse email.

### Gestion des tâches

- Créer une tâche.
- Consulter toutes les tâches.
- Consulter une tâche par son identifiant.
- Modifier une tâche.
- Supprimer une tâche.
- Affecter une tâche à un utilisateur.
- Modifier le statut d'une tâche.
- Définir la priorité d'une tâche.
- Rechercher une tâche par titre.
- Filtrer les tâches par statut.
- Filtrer les tâches par priorité.
- Filtrer les tâches par utilisateur.

### Statuts disponibles

| Statut     | Description                 |
| ---------- | --------------------------- |
| `A_FAIRE`  | Tâche à réaliser            |
| `EN_COURS` | Tâche actuellement en cours |
| `TERMINEE` | Tâche terminée              |

### Priorités disponibles

| Priorité  | Description      |
| --------- | ---------------- |
| `BASSE`   | Faible priorité  |
| `MOYENNE` | Priorité moyenne |
| `HAUTE`   | Priorité élevée  |

---

## Technologies utilisées

| Technologie                     | Utilisation                            |
| ------------------------------- | -------------------------------------- |
| **Java 17**                     | Langage de programmation               |
| **Spring Boot**                 | Framework principal                    |
| **Spring Web**                  | Développement de l'API REST            |
| **Spring Data JPA**             | Accès aux données et persistance       |
| **H2 Database**                 | Base de données en mémoire             |
| **Springdoc OpenAPI / Swagger** | Documentation et test de l'API         |
| **Maven**                       | Gestion des dépendances et compilation |
| **Git**                         | Gestion des versions                   |
| **GitHub**                      | Hébergement du repository              |

---

## Architecture du projet

Le projet respecte une **architecture en couches**, permettant de séparer les différentes responsabilités de l'application.

```text
com.example.taskmanager
│
├── config
│   ├── DataInitializer.java
│   └── OpenApiConfig.java
│
├── controller
│   ├── TaskController.java
│   └── UserController.java
│
├── entity
│   ├── Task.java
│   └── User.java
│
├── enums
│   ├── Priorite.java
│   └── Statut.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
│
├── repository
│   ├── TaskRepository.java
│   └── UserRepository.java
│
└── service
    ├── TaskService.java
    └── UserService.java
```

### Rôle des différentes couches

- **`entity`** : contient les entités JPA `User` et `Task`.
- **`repository`** : assure l'accès aux données avec Spring Data JPA.
- **`service`** : contient la logique métier de l'application.
- **`controller`** : expose les différentes routes REST.
- **`exception`** : centralise la gestion des erreurs et exceptions.
- **`config`** : contient la configuration de Swagger et l'initialisation des données.
- **`enums`** : contient les énumérations utilisées pour les statuts et les priorités.

---

## Prérequis

Pour lancer l'application, les éléments suivants doivent être installés :

- **Java JDK 17** ou supérieur ;
- **Maven**, ou le **Maven Wrapper** fourni avec le projet ;
- **Git**.

Vérifier les versions installées :

```bash
java -version
mvn -version
git --version
```

---

## Installation

### 1. Cloner le repository

```bash
git clone https://github.com/Vicenzaa/taskmanager-uvs.git
```

### 2. Se déplacer dans le dossier du projet

```bash
cd taskmanager-uvs
```

---

## Lancement de l'application

### macOS / Linux

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

Une fois l'application démarrée, elle est accessible à :

http://localhost:8080

---

## URLs importantes

| Service      | URL                                         |
| ------------ | ------------------------------------------- |
| Application  | http://localhost:8080                       |
| Swagger UI   | http://localhost:8080/swagger-ui/index.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs           |
| Console H2   | http://localhost:8080/h2-console            |

---

## Base de données

L'application utilise **H2 Database** avec une base de données en mémoire.

### Paramètres de connexion

| Paramètre | Valeur                      |
| --------- | --------------------------- |
| JDBC URL  | `jdbc:h2:mem:taskmanagerdb` |
| User Name | `sa`                        |
| Password  | _(vide)_                    |

La base de données étant en mémoire, **les données sont réinitialisées à chaque redémarrage de l'application**.

---

## Données de test

Au démarrage de l'application, des données de test sont automatiquement insérées.

### Utilisateurs

|   # | Nom    | Prénom | Email                                                     |
| --: | ------ | ------ | --------------------------------------------------------- |
|   1 | Dupont | Jean   | [jean.dupont@email.com](mailto:jean.dupont@email.com)     |
|   2 | Martin | Sophie | [sophie.martin@email.com](mailto:sophie.martin@email.com) |

### Tâches

|   # | Titre                            | Statut     | Priorité  | Utilisateur   |
| --: | -------------------------------- | ---------- | --------- | ------------- |
|   1 | Développer le module utilisateur | `EN_COURS` | `HAUTE`   | Jean Dupont   |
|   2 | Configurer Swagger               | `A_FAIRE`  | `MOYENNE` | Sophie Martin |
|   3 | Écrire le README                 | `TERMINEE` | `BASSE`   | Jean Dupont   |

---

# Routes API

## Utilisateurs

### Créer un utilisateur

```http
POST /api/users
```

**Exemple :**

```bash
curl -X POST "http://localhost:8080/api/users" \
-H "Content-Type: application/json" \
-d '{
  "nom": "Ndiaye",
  "prenom": "Awa",
  "email": "awa.ndiaye@email.com"
}'
```

**Réponse attendue :**

```text
HTTP 201 CREATED
```

---

### Consulter tous les utilisateurs

```http
GET /api/users
```

---

### Consulter un utilisateur par son ID

```http
GET /api/users/{id}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/users/1"
```

---

## Tâches

### Créer une tâche

```http
POST /api/tasks?utilisateurId={id}
```

Cette route permet de créer une tâche et de l'affecter directement à un utilisateur existant.

**Exemple :**

```bash
curl -X POST "http://localhost:8080/api/tasks?utilisateurId=1" \
-H "Content-Type: application/json" \
-d '{
  "titre": "Réunion projet",
  "description": "Préparer la démonstration Spring Boot",
  "dateLimite": "2026-08-25",
  "priorite": "HAUTE",
  "statut": "A_FAIRE"
}'
```

**Réponse attendue :**

```text
HTTP 201 CREATED
```

---

### Afficher toutes les tâches

```http
GET /api/tasks
```

---

### Afficher une tâche par son ID

```http
GET /api/tasks/{id}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/tasks/1"
```

---

### Modifier une tâche

```http
PUT /api/tasks/{id}
```

---

### Supprimer une tâche

```http
DELETE /api/tasks/{id}
```

---

### Modifier le statut d'une tâche

```http
PATCH /api/tasks/{id}/statut?statut={statut}
```

**Exemple :**

```bash
curl -X PATCH \
"http://localhost:8080/api/tasks/1/statut?statut=EN_COURS"
```

---

## Recherche et filtres

### Rechercher une tâche par titre

```http
GET /api/tasks/search?titre={mot-cle}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/tasks/search?titre=réunion"
```

### Filtrer par statut

```http
GET /api/tasks/filter?statut={statut}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/tasks/filter?statut=EN_COURS"
```

### Filtrer par priorité

```http
GET /api/tasks/filter?priorite={priorite}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/tasks/filter?priorite=HAUTE"
```

### Filtrer par utilisateur

```http
GET /api/tasks/filter?utilisateurId={id}
```

**Exemple :**

```bash
curl "http://localhost:8080/api/tasks/filter?utilisateurId=1"
```

### Combiner plusieurs filtres

```http
GET /api/tasks/filter?statut=A_FAIRE&priorite=HAUTE&utilisateurId=1
```

---

## Gestion des erreurs

|                   Code HTTP | Situation                  |
| --------------------------: | -------------------------- |
|           `400 BAD REQUEST` | Données envoyées invalides |
|             `404 NOT FOUND` | Ressource inexistante      |
| `500 INTERNAL SERVER ERROR` | Erreur interne du serveur  |

### Exemple : ressource inexistante

```http
GET /api/users/999
```

Réponse :

```json
{
  "timestamp": "...",
  "status": 404,
  "error": "Not Found",
  "message": "Utilisateur non trouvé avec l'id: 999"
}
```

### Exemples d'erreurs `400 BAD REQUEST`

- Email vide ;
- Email invalide ;
- Email déjà utilisé ;
- Titre de tâche vide ;
- Statut invalide ;
- Priorité invalide ;
- Utilisateur inexistant lors de l'affectation d'une tâche.

---

## Validation des données

### Utilisateur

- Le nom est obligatoire.
- Le prénom est obligatoire.
- L'email est obligatoire.
- L'email doit respecter un format valide.
- L'email doit être unique.

### Tâche

- Le titre est obligatoire.
- La priorité doit être `BASSE`, `MOYENNE` ou `HAUTE`.
- Le statut doit être `A_FAIRE`, `EN_COURS` ou `TERMINEE`.
- La tâche doit être associée à un utilisateur existant.

---

## Documentation Swagger

La documentation interactive de l'API est disponible à :

http://localhost:8080/swagger-ui/index.html

Elle permet de :

- consulter les endpoints disponibles ;
- visualiser les paramètres des requêtes ;
- consulter les modèles de données ;
- exécuter directement les requêtes ;
- tester les fonctionnalités de l'API depuis le navigateur.

La documentation OpenAPI au format JSON est disponible à :

http://localhost:8080/v3/api-docs

---

## Démonstration

Pour effectuer une démonstration complète de l'application :

1. Lancer l'application avec Maven.
2. Ouvrir Swagger UI dans le navigateur.
3. Consulter les utilisateurs existants.
4. Consulter les tâches existantes.
5. Créer un nouvel utilisateur.
6. Créer une tâche et l'affecter à cet utilisateur.
7. Modifier le statut de la tâche.
8. Rechercher une tâche par titre.
9. Filtrer les tâches par statut.
10. Filtrer les tâches par priorité.
11. Filtrer les tâches par utilisateur.
12. Modifier une tâche.
13. Supprimer une tâche.

---

## Commandes utiles

### Compiler le projet

```bash
./mvnw clean compile
```

### Lancer l'application

```bash
./mvnw spring-boot:run
```

### Générer le package JAR

```bash
./mvnw clean package
```

### Lancer le JAR

```bash
java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
```

---

## Auteur

**Adja Yacine Bar**

Repository GitHub :

https://github.com/Vicenzaa/taskmanager-uvs
