#!/bin/bash

# Script pour construire et déployer les microservices avec Docker Compose

set -e

echo "🚀 Construction et déploiement des microservices..."

# Construire tous les projets Maven
echo "📦 Construction des projets Maven..."
mvn clean package -DskipTests

# Arrêter les conteneurs existants
echo "🛑 Arrêt des conteneurs existants..."
docker-compose down

# Construire et démarrer tous les services
echo "🐳 Construction et démarrage des conteneurs Docker..."
docker-compose up --build -d

# Attendre que les services soient prêts
echo "⏳ Attente du démarrage des services..."
sleep 30

# Vérifier l'état des services
echo "🔍 Vérification de l'état des services..."
docker-compose ps

echo "✅ Déploiement terminé!"
echo ""
echo "📋 Services disponibles:"
echo "  - Eureka Discovery: http://localhost:8761"
echo "  - Config Server: http://localhost:8888"
echo "  - Gateway: http://localhost:8080"
echo "  - Permission Service: http://localhost:8081"
echo "  - Role Service: http://localhost:8082"
echo "  - User Service: http://localhost:8083"
echo "  - PostgreSQL: localhost:5432"
echo "  - Zipkin (Tracing): http://localhost:9411"
echo ""
echo "� Documentation API (Swagger):"
echo "  - Gateway Swagger: http://localhost:8080/swagger-ui.html"
echo "  - Permission Service: http://localhost:8081/swagger-ui.html"
echo "  - Role Service: http://localhost:8082/swagger-ui.html"
echo "  - User Service: http://localhost:8083/swagger-ui.html"
echo ""
echo "�📊 Pour voir les logs: docker-compose logs -f [service-name]"
echo "🛑 Pour arrêter: docker-compose down"
