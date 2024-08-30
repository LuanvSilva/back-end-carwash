# Etapa de build usando Maven
FROM maven:3.9.9-eclipse-temurin-22 AS build

WORKDIR /app

# Copia o arquivo pom.xml e instala as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte do projeto
COPY src ./src

# Compila o projeto
RUN mvn clean install

# Etapa de runtime usando OpenJDK
FROM eclipse-temurin:22-jre-alpine

# Copia o arquivo JAR gerado na etapa de build
COPY --from=build /app/target/*.jar /app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java","-jar","/app.jar"]
