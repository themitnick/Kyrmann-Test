#!/bin/bash

# Script pour ouvrir rapidement tous les liens utiles dans le navigateur

echo "🌐 Ouverture des interfaces web AssurePlus..."

# Fonction pour ouvrir une URL
open_url() {
    if command -v xdg-open > /dev/null; then
        xdg-open "$1" > /dev/null 2>&1 &
    elif command -v open > /dev/null; then
        open "$1" > /dev/null 2>&1 &
    elif command -v start > /dev/null; then
        start "$1" > /dev/null 2>&1 &
    else
        echo "❌ Impossible d'ouvrir automatiquement. Copiez cette URL : $1"
    fi
}

# Attendre que les services soient prêts
echo "⏳ Attente du démarrage des services (30 secondes)..."
sleep 30

echo ""
echo "🔧 Ouverture des interfaces d'administration..."

# Services d'infrastructure
echo "📊 Eureka Discovery Dashboard..."
open_url "http://localhost:8761"

echo "🎯 Zipkin Tracing..."
open_url "http://localhost:9411"

sleep 2

echo ""
echo "📖 Ouverture de la documentation API..."

# Documentation Swagger
echo "🌐 Gateway Swagger (Vue d'ensemble)..."
open_url "http://localhost:8080/swagger-ui.html"

sleep 1

echo "👤 User Service API..."
open_url "http://localhost:8083/swagger-ui.html"

sleep 1

echo "🔐 Role Service API..."
open_url "http://localhost:8082/swagger-ui.html"

sleep 1

echo "🛡️ Permission Service API..."
open_url "http://localhost:8081/swagger-ui.html"

echo ""
echo "✅ Toutes les interfaces ont été ouvertes!"
echo ""
echo "📋 URLs disponibles:"
echo "  🌐 Gateway: http://localhost:8080"
echo "  📊 Eureka: http://localhost:8761"
echo "  🎯 Zipkin: http://localhost:9411"
echo "  📖 Swagger (Gateway): http://localhost:8080/swagger-ui.html"
echo "  👤 User API: http://localhost:8083/swagger-ui.html"
echo "  🔐 Role API: http://localhost:8082/swagger-ui.html"
echo "  🛡️ Permission API: http://localhost:8081/swagger-ui.html"
