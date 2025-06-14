FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia os arquivos de build
COPY target/*.jar app.jar

# Roda a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
