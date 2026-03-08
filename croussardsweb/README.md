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

## Configuration Technique

### Liaison avec l'API
Pour fonctionner, ce frontend doit être connecté au backend. La configuration se trouve dans :
`src/main/resources/application.properties`

Assurez-vous que l'URL de l'API est correcte :
```properties
# Exemple de configuration pour pointer vers le backend
api.server.url=http://localhost:9007
```
