# Etapa 1: Construcción
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos y compilamos el módulo común
COPY commonModule/pom.xml /app/commonModule/pom.xml
COPY commonModule/src /app/commonModule/src
RUN mvn -f /app/commonModule clean install -DskipTests

# Copiamos y compilamos el servicio REST
COPY servicioRest/pom.xml /app/servicioRest/pom.xml
COPY servicioRest/src /app/servicioRest/src
RUN mvn -f /app/servicioRest clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/servicioRest/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]