# ==========================================
# Compilación óptima de Quarkus
# ==========================================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos el archivo de configuración de dependencias de Quarkus
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente e infraestructura gRPC (.proto)
COPY src ./src

# Compilamos construyendo la estructura Fast-JAR de Quarkus
RUN mvn clean package -DskipTests

# ==========================================
# Entorno de ejecución Quarkus de alta velocidad
# ==========================================
FROM eclipse-temurin:21-jre
WORKDIR /app

# En lugar de un solo .jar, Quarkus requiere toda la estructura generada
COPY --from=build /app/target/quarkus-app/ ./

# Informamos los puertos en uso: 8081 (HTTP) y 9091 (gRPC)
EXPOSE 8081

# ====================================================================
# ¡AUTOMATIZACIÓN DE PUERTOS Y GRPC!
# ====================================================================
# Forzamos a que gRPC comparta el mismo puerto que el servidor HTTP
ENV QUARKUS_GRPC_SERVER_USE_SEPARATE_SERVER=false

# Definimos el puerto por defecto de este contenedor (Instancia 1)
ENV QUARKUS_HTTP_PORT=8081

# Forzamos a que escuche en todas las interfaces de red del contenedor
ENV QUARKUS_HTTP_HOST=0.0.0.0
# ====================================================================

# Comando especializado para arrancar el Fast-JAR ejecutable de Quarkus
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]