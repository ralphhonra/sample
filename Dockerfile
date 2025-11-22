FROM amazoncorretto:17-alpine-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM ibm-semeru-runtimes:open-17.0.17_9-jre
WORKDIR /app
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]