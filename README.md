# 🏢 AssurePlus - Architecture Microservices

Système de gestion d'assurance basé sur une architecture microservices avec Spring Boot, Spring Cloud et Docker.

## 📋 Table des matières

- [Architecture](#-architecture)
- [Services](#-services)
- [Technologies](#-technologies)
- [Prérequis](#-prérequis)
- [Installation et déploiement](#-installation-et-déploiement)
- [URLs et accès](#-urls-et-accès)
- [Documentation API](#-documentation-api)
- [Monitoring et observabilité](#-monitoring-et-observabilité)
- [Développement](#-développement)

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Gateway       │    │   Discovery     │    │  Config Server  │
│   Port: 8080    │    │   Port: 8761    │    │   Port: 8888    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
    ┌────────────────────────────┼────────────────────────────┐
    │                            │                            │
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Permission      │    │  Role Service   │    │  User Service   │
│ Service         │    │  Port: 8082     │    │  Port: 8083     │
│ Port: 8081      │    └─────────────────┘    └─────────────────┘
└─────────────────┘              │                       │
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────┐
                    │   PostgreSQL    │
                    │   Port: 5432    │
                    └─────────────────┘
```

## 🔧 Services

### 🛡️ Infrastructure Services

| Service           | Port | Description                                   | Repository                         |
| ----------------- | ---- | --------------------------------------------- | ---------------------------------- |
| **Gateway**       | 8080 | API Gateway principal - Point d'entrée unique | [./gateway](./gateway)             |
| **Discovery**     | 8761 | Service de découverte Eureka                  | [./discovery](./discovery)         |
| **Config Server** | 8888 | Serveur de configuration centralisée          | [./config-server](./config-server) |

### 💼 Business Services

| Service                | Port | Description              | Repository                                   |
| ---------------------- | ---- | ------------------------ | -------------------------------------------- |
| **User Service**       | 8083 | Gestion des utilisateurs | [./user-service](./user-service)             |
| **Role Service**       | 8082 | Gestion des rôles        | [./role-service](./role-service)             |
| **Permission Service** | 8081 | Gestion des permissions  | [./permission-service](./permission-service) |

### 🗄️ Data & Monitoring

| Service        | Port | Description                |
| -------------- | ---- | -------------------------- |
| **PostgreSQL** | 5432 | Base de données principale |
| **Zipkin**     | 9411 | Tracing distribué          |

## 🛠️ Technologies

- **Java 17** - Langage de programmation
- **Spring Boot 3.5.3** - Framework principal
- **Spring Cloud 2025.0.0** - Microservices
- **PostgreSQL 15** - Base de données
- **Docker & Docker Compose** - Conteneurisation
- **Zipkin** - Tracing distribué
- **Swagger/OpenAPI 3** - Documentation API
- **Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **Flyway** - Migration de base de données
- **MapStruct** - Mapping d'objets
- **Resilience4j** - Circuit breaker et retry

## 📋 Prérequis

- **Java 17** ou supérieur
- **Maven 3.8+**
- **Docker** et **Docker Compose**
- **Git**

## 🚀 Installation et déploiement

### Méthode rapide (Recommandée)

```bash
# Cloner le projet
git clone https://github.com/themitnick/Kyrmann-Test.git
cd AssurePlus

# Déployer tous les services
./deploy.sh
```

### Méthode manuelle

```bash
# 1. Construire tous les projets
mvn clean package -DskipTests

# 2. Démarrer les services avec Docker Compose
docker-compose up --build -d

# 3. Vérifier le statut
docker-compose ps
```

## 🌐 URLs et accès

### 🔗 Services principaux

| Service              | URL                   | Description                       |
| -------------------- | --------------------- | --------------------------------- |
| **Gateway**          | http://localhost:8080 | Point d'entrée principal          |
| **Eureka Dashboard** | http://localhost:8761 | Console de découverte de services |
| **Config Server**    | http://localhost:8888 | Serveur de configuration          |

### 📊 APIs métier

| Service             | URL                                    | Description                            |
| ------------------- | -------------------------------------- | -------------------------------------- |
| **Users API**       | http://localhost:8080/api/utilisateurs | Gestion des utilisateurs (via Gateway) |
| **Roles API**       | http://localhost:8080/api/roles        | Gestion des rôles (via Gateway)        |
| **Permissions API** | http://localhost:8080/api/permissions  | Gestion des permissions (via Gateway)  |

### 🔍 URLs directes (développement)

| Service                | URL                   | Description                          |
| ---------------------- | --------------------- | ------------------------------------ |
| **User Service**       | http://localhost:8083 | Accès direct au service utilisateurs |
| **Role Service**       | http://localhost:8082 | Accès direct au service rôles        |
| **Permission Service** | http://localhost:8081 | Accès direct au service permissions  |

## 📖 Documentation API

### 🎯 Swagger UI

| Service                | Swagger URL                           | API Docs JSON                  |
| ---------------------- | ------------------------------------- | ------------------------------ |
| **Gateway (Agrégé)**   | http://localhost:8080/swagger-ui.html | -                              |
| **User Service**       | http://localhost:8083/swagger-ui.html | http://localhost:8083/api-docs |
| **Role Service**       | http://localhost:8082/swagger-ui.html | http://localhost:8082/api-docs |
| **Permission Service** | http://localhost:8081/swagger-ui.html | http://localhost:8081/api-docs |

### 📋 Endpoints principaux

#### User Service

- `GET /api/utilisateurs` - Liste des utilisateurs
- `POST /api/utilisateurs` - Créer un utilisateur
- `GET /api/utilisateurs/{id}` - Détails d'un utilisateur
- `PUT /api/utilisateurs/{id}` - Modifier un utilisateur
- `DELETE /api/utilisateurs/{id}` - Supprimer un utilisateur

#### Role Service

- `GET /api/roles` - Liste des rôles
- `POST /api/roles` - Créer un rôle
- `GET /api/roles/{id}` - Détails d'un rôle

#### Permission Service

- `GET /api/permissions` - Liste des permissions
- `POST /api/permissions` - Créer une permission
- `GET /api/permissions/{id}` - Détails d'une permission

## 📊 Monitoring et observabilité

### 🔍 Tracing

| Service    | URL                   | Description                    |
| ---------- | --------------------- | ------------------------------ |
| **Zipkin** | http://localhost:9411 | Interface de tracing distribué |

### 💊 Health Checks

| Service                | Health URL                            | Description                   |
| ---------------------- | ------------------------------------- | ----------------------------- |
| **Gateway**            | http://localhost:8080/actuator/health | Santé du gateway              |
| **User Service**       | http://localhost:8083/actuator/health | Santé du service utilisateurs |
| **Role Service**       | http://localhost:8082/actuator/health | Santé du service rôles        |
| **Permission Service** | http://localhost:8081/actuator/health | Santé du service permissions  |

### 📈 Métriques

Toutes les métriques sont disponibles via l'endpoint `/actuator/metrics` de chaque service.

## 🛠️ Développement

### 📁 Structure du projet

```
.
├── deploy.sh                 # Script de déploiement
├── docker-compose.yml        # Configuration Docker Compose
├── README.md                # Documentation principale
├── common/                  # Module commun partagé
├── config-server/           # Serveur de configuration
├── discovery/               # Service de découverte Eureka
├── gateway/                 # API Gateway
├── permission-service/      # Service de permissions
├── role-service/           # Service de rôles
└── user-service/           # Service d'utilisateurs
```

### 🔧 Commandes utiles

```bash
# Voir les logs de tous les services
docker-compose logs -f

# Voir les logs d'un service spécifique
docker-compose logs -f user-service

# Redémarrer un service
docker-compose restart user-service

# Arrêter tous les services
docker-compose down

# Supprimer les volumes (attention : perte de données)
docker-compose down -v

# Reconstruire et redémarrer
docker-compose up --build -d

# Voir l'état des services
docker-compose ps
```

### 🔄 Redéploiement après modifications

```bash
# Reconstruire et redéployer un service spécifique
docker-compose build user-service
docker-compose up -d user-service

# Redéploiement complet
./deploy.sh
```

### 🐛 Débogage

1. **Vérifier les logs** :

   ```bash
   docker-compose logs -f [service-name]
   ```

2. **Vérifier la connectivité réseau** :

   ```bash
   docker-compose exec user-service ping postgres
   ```

3. **Accéder à un conteneur** :
   ```bash
   docker-compose exec user-service bash
   ```

## 🗃️ Base de données

### 🔗 Connexion

- **Host** : localhost (ou postgres dans Docker)
- **Port** : 5432
- **Database** : assurePlusBD
- **Username** : kse
- **Password** : @Kse12345

### 🔄 Migrations

Les migrations Flyway sont automatiquement exécutées au démarrage de chaque service.

## 📞 Support

Pour toute question ou problème :

- 📧 Email : support@assureplus.com
- 📚 Documentation : Consultez les README de chaque service
- 🐛 Issues : Créez une issue dans le repository Git

---

🎉 **Happy Coding!** Développé avec ❤️ par l'équipe AssurePlus
