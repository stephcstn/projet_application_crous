# Croussards Web - Interface Étudiante

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green.svg)
![Thymeleaf](https://img.shields.io/badge/Template-Thymeleaf-005F0F.svg)
![CSS3](https://img.shields.io/badge/Style-CSS3-1572B6.svg)

## Présentation

Ce module constitue l'interface utilisateur (Frontend) du projet **Croussards**. Développé avec **Thymeleaf**, il permet aux étudiants de l'Université de Poitiers d'interagir visuellement avec les services du CROUS. 

L'application consomme les données fournies par l'API `croussardsapi` pour afficher les informations en temps réel.

## Architecture Frontend

L'application suit le modèle **MVC (Modèle-Vue-Contrôleur)** :

* **Controllers** : Gèrent la navigation et les appels aux services (ex: `RestaurantController`, `StudentController`).
* **Services & Proxies** : Utilisent `RestTemplate` ou `WebClient` pour récupérer les données de l'API Backend.
* **Templates (Views)** : Pages HTML dynamiques situées dans `src/main/resources/templates`.
* **Static Assets** : Fichiers CSS, images et JavaScript pour l'interface.

## Aperçu des Pages

Le frontend inclut actuellement les vues suivantes :
* **Accueil** (`home.html`) : Présentation de la plateforme.
* **Espace Étudiant** : Inscription (`register.html`) et Connexion (`login.html`).
* **Restaurants** : Liste complète (`restaurants.html`) et détails avec avis (`restaurant_reviews.html`).
* **Avis** : Formulaire de dépôt d'avis (`review_form.html`).

## 🚀 Comment lancer l'application (Multi-module)

Pour que l'interface fonctionne, vous devez impérativement lancer le **Backend** et le **Frontend** en même temps.

### 1. Lancement via IntelliJ IDEA
1. **Démarrer l'API (Backend)** :
   * Allez dans le module `croussardsapi`.
   * Ouvrez la classe `CroussardsApplication.java`.
   * Cliquez sur la flèche verte (**Run**).
   * L'API sera active sur : `http://localhost:9007`.

2. **Démarrer l'Interface (Web)** :
   * Allez dans le module `croussardsweb`.
   * Ouvrez la classe `CroussardsWebApplication.java`.
   * Cliquez sur la flèche verte (**Run**).
   * L'interface sera active sur : `http://localhost:9008`.


### 2. Vérification de la communication
Une fois les deux serveurs lancés, le Frontend (`9008`) va automatiquement envoyer des requêtes au Backend (`9007`) via les classes **Proxy**. 

* **Test rapide** : Accédez à `http://localhost:9008/students`. Si la liste s'affiche (même vide), la connexion est établie.
* **Problème de port ?** Si l'un des ports est déjà utilisé, vérifiez les fichiers `application.properties` respectifs :
  * Backend : `server.port=9007`
  * Frontend : `server.port=9008`

## État d'avancement (L3 Gestion de Projet)
* **Complété** : Structure globale, navigation, affichage des listes de restaurants et d'étudiants.
* **En cours** : Finalisation du design CSS , intégration complète du formulaire d'avis et Gestion des sessions et persistance du login via le proxy étudiant.

