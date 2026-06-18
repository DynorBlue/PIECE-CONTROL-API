# === Paso 1: Compilar la aplicación ===
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos todo el contenido del repositorio
COPY . .

# Compilamos la aplicación
RUN mvn clean package -DskipTests

# === Paso 2: Crear la imagen de ejecución ===
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# COPIA EXACTA: Traemos el jar desde la ruta donde Maven lo acaba de generar
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8081

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]