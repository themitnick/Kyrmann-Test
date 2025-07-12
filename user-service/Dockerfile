FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le fichier JAR de l'application
COPY target/user-service-*.jar app.jar

# Exposer le port
EXPOSE 8083

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
