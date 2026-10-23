# Croussards — les avis étudiants sur la restauration universitaire

![Java 21](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7%20%7C%204.0.0-6DB33F?logo=springboot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Templates-Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)
![JPA](https://img.shields.io/badge/Persistence-JPA%20%2F%20Hibernate-59666C)
![H2](https://img.shields.io/badge/Database-H2-09476B)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white)

**Croussards** est une plateforme web de consultation et de partage d’avis sur les restaurants universitaires. Le projet a été réalisé en **L3 Informatique à l’Université de Poitiers**, dans le cadre de l’UE Gestion de Projet.

Pour un recruteur ou une équipe technique, le projet illustre une architecture client-serveur distribuée en deux applications Spring Boot, la séparation entre interface et API, la consommation de services REST et la gestion de sessions HTTP côté serveur. Il met également en pratique la persistance relationnelle avec JPA/Hibernate et la validation des données.

## Fonctionnalités

- Parcourir les restaurants et consulter leur fiche et les avis associés.
- Créer un compte étudiant, se connecter et se déconnecter.
- Accéder à une session étudiante et retrouver les avis associés au profil.
- Publier un avis sur un restaurant avec une note de **1 à 5 étoiles**, un commentaire et une date de publication.
- Consulter les avis par restaurant ou par étudiant.
- Gérer les données étudiants, restaurants et avis au moyen de l’API REST.

Les notes sont enregistrées et validées individuellement entre 1 et 5. Le calcul d’une moyenne globale par restaurant n’est pas présenté comme une fonctionnalité implémentée.

## Architecture

Les deux applications sont découplées et démarrent séparément. Le navigateur communique avec l’application Web ; celle-ci appelle l’API REST avec `RestTemplate`. L’API applique les règles de validation et accède aux données via JPA/Hibernate.

```text
┌───────────────────────┐       HTTP        ┌──────────────────────┐
│ Navigateur étudiant   │ ────────────────► │ Application Web      │
│                       │ ◄──────────────── │ Spring MVC +         │
└───────────────────────┘    HTML rendu     │ Thymeleaf            │
                                             │ Port 9008            │
                                             └──────────┬───────────┘
                                                        │
                                                 REST / JSON
                                                 RestTemplate
                                                        │
                                                        ▼
                                             ┌──────────────────────┐
                                             │ API REST             │
                                             │ Spring Boot          │
                                             │ Port 9007            │
                                             └──────────┬───────────┘
                                                        │
                                                  JPA / Hibernate
                                                        │
                                                        ▼
                                             ┌──────────────────────┐
                                             │ Base H2 en mémoire   │
                                             └──────────────────────┘
```

### Responsabilités des modules

| Module | Port | Responsabilités |
|---|---:|---|
| `croussardsapi` | `9007` | API REST des étudiants, restaurants et avis ; validation des requêtes ; persistance JPA/Hibernate ; base H2. |
| `croussardsweb` | `9008` | Contrôleurs Spring MVC, vues Thymeleaf, session HTTP et appels à l’API via des proxies `RestTemplate`. |

Quelques routes exposées par l’API :

| Ressource | Exemples de routes |
|---|---|
| Restaurants | `GET /restaurants`, `GET /restaurants/{id}`, `GET /restaurants/{id}/reviews` |
| Étudiants | `GET /students`, `POST /students`, `POST /students/login`, `GET /students/{id}/reviews` |
| Avis | `GET /reviews`, `GET /reviews/{id}`, `POST /reviews/save` |

## Choix techniques et apprentissages

- **Séparation des responsabilités** : le module Web s’occupe de l’expérience utilisateur et de la session ; l’API centralise les ressources et leur persistance.
- **Consommation d’API REST** : les proxies du client Web encapsulent les échanges HTTP/JSON vers l’API, configurée à `http://localhost:9007`.
- **Sessions HTTP** : l’identifiant et le nom de l’étudiant connecté sont conservés dans la session du serveur Web ; l’accès au formulaire de dépôt d’avis requiert une session étudiante.
- **Persistance et validation** : les entités sont gérées avec JPA/Hibernate ; les notes sont bornées de 1 à 5 et les données de requête sont validées côté API.
- **Relations JPA ciblées** : les avis référencent un étudiant et un restaurant via des relations `ManyToOne`. Les entités `Student` et `Restaurant` ne portent pas de collections inverses d’avis, ce qui limite les relations bidirectionnelles et simplifie la sérialisation JSON.
- **Environnement local léger** : la base H2 en mémoire et les données d’exemple permettent de démarrer et d’explorer le projet sans configurer de serveur de base externe. Les données sont réinitialisées au redémarrage de l’API.

Les modules utilisent Java 21 et des versions Spring Boot définies séparément dans leurs fichiers Maven : **3.5.7 pour l’API** et **4.0.0 pour l’application Web**.

## Lancer le projet en local

### Prérequis

- **IntelliJ IDEA** (Community ou Ultimate)
- **JDK 21**
- Les ports `9007` et `9008` doivent être disponibles.

### 1. Importer le projet

Ouvrez le dépôt dans IntelliJ IDEA et importez les deux modules Maven, `croussardsapi` et `croussardsweb`. Vérifiez que le SDK du projet est configuré sur **Java 21** et que les dépendances Maven sont chargées.

### 2. Démarrer l’API

Dans le module `croussardsapi`, ouvrez la classe `CroussardsApplication` puis cliquez sur **Run** dans la gouttière de l’éditeur (ou utilisez le menu **Run** d’IntelliJ IDEA).

L’API est disponible à l’adresse **http://localhost:9007**. Elle initialise la base H2 en mémoire avec les données d’exemple du projet.

### 3. Démarrer l’application Web

Une fois l’API démarrée, ouvrez la classe `CroussardsWebApplication` dans le module `croussardsweb` et cliquez également sur **Run**. Laissez les deux configurations d’exécution actives.

L’interface est disponible à l’adresse **http://localhost:9008**. Elle nécessite que l’API soit déjà lancée.

### 4. Explorer l’application

- Accueil : [http://localhost:9008/](http://localhost:9008/)
- Restaurants : [http://localhost:9008/restaurants](http://localhost:9008/restaurants)
- Étudiants : [http://localhost:9008/students](http://localhost:9008/students)
- Connexion : [http://localhost:9008/login](http://localhost:9008/login)

Les données de démonstration initialisées par l’API comprennent notamment des étudiants, restaurants et avis.

### Alternative : démarrer avec Maven Wrapper

Chaque module contient son propre Maven Wrapper. Ouvrez un terminal dans le dossier du module concerné — par exemple le terminal intégré d’IntelliJ IDEA — et utilisez la commande correspondant à votre système.

Depuis `croussardsapi`, démarrez d’abord l’API :

```bash
./mvnw spring-boot:run
```

Sous Windows (Invite de commandes) :

```bat
mvnw.cmd spring-boot:run
```

Puis, dans un second terminal ouvert dans `croussardsweb`, démarrez l’application Web :

```bash
./mvnw spring-boot:run
```

Sous Windows (Invite de commandes) :

```bat
mvnw.cmd spring-boot:run
```

### Exécuter les tests

Dans IntelliJ IDEA, ouvrez la classe de test du module concerné, puis cliquez sur l’icône **Run** à côté de la classe ou de la méthode de test. Les tests de l’API et de l’application Web se trouvent dans leurs dossiers `src/test/java` respectifs.

Il est également possible de lancer les tests avec le wrapper, depuis le dossier de chaque module :

```bash
./mvnw test
```

Sous Windows (Invite de commandes) :

```bat
mvnw.cmd test
```

## Équipe

Projet réalisé en équipe dans le cadre de la **L3 Informatique — Université de Poitiers**.

| Membre | Contribution |
|---|---|
| **Stéphane CASTAN** | Conception et développement du projet en équipe |
| **Basile DUFRENE** | Conception et développement du projet en équipe |
| **Evan RIBOULEAU** | Conception et développement du projet en équipe |

---

*Projet universitaire — UE Gestion de Projet, L3 Informatique, Université de Poitiers.*
