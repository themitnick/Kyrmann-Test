FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le fichier JAR de l'application
COPY target/config-server-*.jar app.jar

# Exposer le port
EXPOSE 8888

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
