# Croussards API

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green.svg)
![SQL](https://img.shields.io/badge/Language-SQL-lightgrey.svg)
![H2](https://img.shields.io/badge/Database-H2-blue.svg)

## À propos du projet

Cette application a été élaborée dans le cadre de la **L3 Informatique de l'Université de Poitiers**, pour l'unité d'enseignement **UE Gestion de Projet**.

### Objectif
L'objectif principal est d'offrir une plateforme collaborative permettant aux **étudiants de donner et de consulter des avis sur les restaurants du CROUS**. 

Le projet vise à centraliser les informations des structures de restauration et à favoriser l'entraide entre étudiants pour mieux choisir leur lieu de repas quotidien. Pour cela, nous avons conçu un backend avec **Spring Boot** capable de gérer les restaurants, les profils étudiants et la persistance des avis via **SQL/JPA**.


## Structure du Projet

L'architecture suit les standards de Spring Boot (Layered Architecture) :

* **Controller** : Définit les points d'entrée de l'API (REST Endpoints).
* **Service** : Contient la logique métier de l'application.
* **Repository** : Gère l'accès à la base de données via Spring Data JPA.
* **Model** : Définit les entités de la base de données (`Restaurant`, `Student`, `Review`).

## Prérequis

* **JDK 21** ou supérieur (GraalVM 21 utilisé par défaut).
* **Maven** (inclus via le wrapper `./mvnw`).
* Un IDE comme **IntelliJ IDEA**.

## Configuration et Lancement

### 1. Installation des dépendances
Ouvrez un terminal à la racine du projet et exécutez :
```powershell
./mvnw clean install
```

### 2. Lancement de l'application
``` PowerShell
./mvnw spring-boot:run
```
L'API est configurée sur le port 9007 : http://localhost:9007

## Base de Données & SQL

Le projet utilise **H2 en mémoire** avec génération automatique du schéma.

- **Console H2** : [http://localhost:9007/h2-console](http://localhost:9007/h2-console)  
- **JDBC URL** : `jdbc:h2:mem:testdb`

### Requêtes SQL

Hibernate transforme vos objets Java en tables SQL pour assurer la cohérence des données.  
Tables principales générées automatiquement :  

- `RESTAURANT` – Informations sur les restaurants  
- `STUDENT` – Informations sur les étudiants  
- `REVIEW` – Avis et notes des étudiants sur les restaurants

## Endpoints Principaux

| Méthode | Endpoint | Action |
| :--- | :--- | :--- |
| **GET** | `/restaurants` | Liste tous les restaurants du CROUS |
| **GET** | `/restaurants/{id}/reviews` | Voir tous les avis d'un restaurant spécifique |
| **GET** | `/students/{id}/reviews` | Voir l'historique de ses propres avis |
| **POST** | `/reviews` | Permet à un étudiant de publier un nouvel avis |
| **POST** | `/students` | Inscription d'un nouvel étudiant |
| **POST** | `/students/authenticate` | Se connecter à l'application |

## Urls utiles

- IHM Base de données : http://localhost:8080/h2-console
- Documentation Swagger pour intéragir avec l'api Rest : http://localhost:9007/swagger-ui/index.html




