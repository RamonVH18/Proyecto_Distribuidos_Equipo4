# ==========================================
# Compilación del código fuente
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
# Empaquetamos y copiamos las dependencias externas a una carpeta
RUN mvn clean package dependency:copy-dependencies -DskipTests

# ==========================================
# Entorno de ejecución ultra-ligero
# ==========================================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos tu archivo comprimido y la carpeta con sus librerías de MQTT
COPY --from=build /app/target/*.jar ./app.jar
COPY --from=build /app/target/dependency ./lib

# Arrancamos configurando el Classpath (-cp) apuntando a la clase principal de tu pom.xml
ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "com.sensor_biometrico.Sensor_Biometrico"]
