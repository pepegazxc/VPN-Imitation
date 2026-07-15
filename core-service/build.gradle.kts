plugins {
    java
    id("org.springframework.boot") version "4.1.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "me.pepega"
version = "0.0.1-SNAPSHOT"
description = "core-service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Database & Migration (PostgreSQL)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    // In-Memory Data Grid (Redis)
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Messaging (Kafka)
    implementation("org.springframework.boot:spring-boot-starter-kafka")

    // Security (OAuth2 Resource Server)
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server")

    // Internal Modules & gRPC
    implementation(project(":grpc-interface"))
    implementation("net.devh:grpc-client-spring-boot-starter:3.1.0.RELEASE")

    // Specific Tests
    testImplementation("org.springframework.boot:spring-boot-starter-data-redis-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}