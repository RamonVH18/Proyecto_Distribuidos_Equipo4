# ==========================================
# Compilación de código y generación de gRPC
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
#  Generamos las clases gRPC, el JAR y extraemos sus dependencias
RUN mvn clean package dependency:copy-dependencies -DskipTests

# ==========================================
# Imagen de ejecución ligera
# ==========================================
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar
COPY --from=build /app/target/dependency ./lib

# Arrancamos con el Classpath apuntando a la clase principal de Autenticación
ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "com.mycompany.servicio_autenticacion.Servicio_Autenticacion"]
