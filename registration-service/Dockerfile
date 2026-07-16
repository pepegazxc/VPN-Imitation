FROM gradle:8.8-jdk21 AS builder

WORKDIR /vpn-imitation
COPY gradle/ gradle/
COPY gradlew build.gradle.kts settings.gradle.kts ./

COPY . .

RUN ./gradlew :registration-service:bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /registration-service

COPY --from=builder /vpn-imitation/registration-service/build/libs/*.jar registration-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "registration-service.jar"]