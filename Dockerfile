# Utiliser l'image OpenJDK pour Java 22
FROM openjdk:22-slim

# Définir un argument pour le fichier JAR à copier
ARG JAR_FILE=target/*.jar

# Copier le fichier JAR de l'application dans l'image Docker
COPY ${JAR_FILE} app.jar

# Exposer le port 8080 pour l'accès à l'application
EXPOSE 8080

# Définir le point d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
