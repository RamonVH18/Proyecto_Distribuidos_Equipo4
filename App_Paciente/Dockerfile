# ==========================================
# Compilación de código Java 21
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package dependency:copy-dependencies -DskipTests

# ==========================================
#  Entorno de ejecución ligero
# ==========================================
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar
COPY --from=build /app/target/dependency ./lib

# Ejecución apuntando a la clase principal de App_Paciente
ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "com.app_paciente.App_Paciente"]
