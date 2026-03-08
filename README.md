# Croussards - Plateforme d'Avis Restaurants CROUS

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green.svg)
![Thymeleaf](https://img.shields.io/badge/Frontend-Thymeleaf-005F0F.svg)
![SQL](https://img.shields.io/badge/Language-SQL-lightgrey.svg)

## À propos du projet

Cette application a été élaborée dans le cadre de la **L3 Informatique de l'Université de Poitiers**, pour l'unité d'enseignement **UE Gestion de Projet**.

### Objectif
L'objectif est de fournir une plateforme collaborative permettant aux **étudiants de donner et de consulter des avis sur les restaurants du CROUS**. Le système permet de centraliser les informations des structures de restauration et de favoriser l'entraide entre étudiants.

## Architecture du Système

Le projet est divisé en deux modules distincts :

1.  **`croussardsapi` (Backend)** : Une API REST robuste qui gère la logique métier, l'authentification et la persistance des données.
2.  **`croussardsweb` (Frontend)** : Une application Web utilisant **Thymeleaf** pour l'affichage des données. Cette partie est actuellement en cours de développement (Preuve de concept).

## Backend : `croussardsapi`

C'est le cœur du système. Il expose les données via une interface REST.

## Frontend : `croussardsweb`

L'interface utilisateur permet d'interagir avec l'API de manière visuelle.

* **Technologie** : Java avec moteur de templates **Thymeleaf**.
* **État actuel** : Version préliminaire permettant d'afficher la liste des étudiants et des restaurants.
* **Navigation** : Inclut des pages pour l'accueil, la connexion, la liste des étudiants et le détail des restaurants.
