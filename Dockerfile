# === Paso 1: Compilar la aplicación ===
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos todo el contenido del repositorio al contenedor
COPY . .

# Nos movemos a la subcarpeta donde está el pom.xml y compilamos
RUN mvn -f api/pom.xml clean package -DskipTests

# === Paso 2: Crear la imagen de ejecución ===
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiamos el archivo .jar generado desde la etapa de compilación
COPY --from=build /app/api/target/*.jar app.jar

# Exponemos el puerto en el que corre tu API
EXPOSE 8081

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]