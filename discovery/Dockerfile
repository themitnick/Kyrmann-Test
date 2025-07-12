FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le fichier JAR de l'application
COPY target/discovery-*.jar app.jar

# Exposer le port
EXPOSE 8761

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
