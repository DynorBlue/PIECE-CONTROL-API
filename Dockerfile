# === Paso 1: Compilar la aplicación ===
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos todo el contenido del repositorio
COPY . .

# Buscamos el archivo pom.xml dinámicamente y compilamos desde donde esté
RUN mvn clean package -DskipTests

# === Paso 2: Crear la imagen de ejecución ===
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Buscamos el .jar generado dinámicamente sin importar la carpeta interna
COPY --from=build /app/**/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]