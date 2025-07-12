#!/bin/bash

# Script pour exécuter tous les tests unitaires

echo "🧪 Exécution des tests unitaires pour tous les services..."

# Couleurs pour les messages
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Fonction pour exécuter les tests d'un service
run_service_tests() {
    local service_name=$1
    local service_path=$2
    
    echo -e "\n${BLUE}📋 Tests pour ${service_name}...${NC}"
    
    cd "$service_path" || {
        echo -e "${RED}❌ Erreur: Impossible d'accéder au répertoire ${service_path}${NC}"
        return 1
    }
    
    # Exécuter les tests
    if mvn test -q; then
        echo -e "${GREEN}✅ Tests ${service_name} : SUCCÈS${NC}"
        return 0
    else
        echo -e "${RED}❌ Tests ${service_name} : ÉCHEC${NC}"
        return 1
    fi
}

# Répertoire racine du projet
PROJECT_ROOT="/home/nkonan/Documents/Learning/Tests/Test-MS/Code"
cd "$PROJECT_ROOT" || {
    echo -e "${RED}❌ Erreur: Impossible d'accéder au répertoire du projet${NC}"
    exit 1
}

# Compteurs
total_services=0
successful_services=0
failed_services=0

echo -e "${YELLOW}🏗️ Compilation de tous les projets...${NC}"
mvn clean compile -q || {
    echo -e "${RED}❌ Erreur lors de la compilation${NC}"
    exit 1
}

echo -e "${GREEN}✅ Compilation réussie${NC}"

# Services à tester
services=(
    "Permission Service:permission-service"
    "Role Service:role-service"
    "User Service:user-service"
)

# Exécuter les tests pour chaque service
for service_info in "${services[@]}"; do
    IFS=':' read -r service_name service_dir <<< "$service_info"
    service_path="$PROJECT_ROOT/$service_dir"
    
    total_services=$((total_services + 1))
    
    if run_service_tests "$service_name" "$service_path"; then
        successful_services=$((successful_services + 1))
    else
        failed_services=$((failed_services + 1))
    fi
    
    # Retourner au répertoire racine
    cd "$PROJECT_ROOT"
done

# Rapport final
echo -e "\n${BLUE}📊 RAPPORT FINAL DES TESTS${NC}"
echo -e "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo -e "📋 Total des services testés: ${total_services}"
echo -e "${GREEN}✅ Services avec tests réussis: ${successful_services}${NC}"
echo -e "${RED}❌ Services avec tests échoués: ${failed_services}${NC}"

if [ $failed_services -eq 0 ]; then
    echo -e "\n${GREEN}🎉 TOUS LES TESTS SONT PASSÉS AVEC SUCCÈS ! 🎉${NC}"
    exit 0
else
    echo -e "\n${RED}⚠️ Certains tests ont échoué. Veuillez vérifier les logs ci-dessus.${NC}"
    exit 1
fi
