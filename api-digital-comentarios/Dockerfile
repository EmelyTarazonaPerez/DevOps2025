FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el JAR generado con Gradle
COPY build/libs/*.jar comentarios-0.0.1-SNAPSHOT.jar

# Expone el puerto donde corre tu aplicación (cámbialo si no es 8080)
EXPOSE 8083

# Arranque de la app
ENTRYPOINT ["java", "-jar", "comentarios-0.0.1-SNAPSHOT.jar"]
