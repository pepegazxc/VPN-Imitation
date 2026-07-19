plugins {
    java
    id("org.springframework.boot") version "4.1.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "me.pepega"
version = "0.0.1-SNAPSHOT"
description = "registration-service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Database, Flyway & Cache
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    // Messaging (Kafka)
    implementation("org.springframework.boot:spring-boot-starter-kafka")

    // Security & OAuth2
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server")
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // GRPC & Contracts
    implementation(project(":grpc-interface"))
    implementation("net.devh:grpc-client-spring-boot-starter:3.1.0.RELEASE")

    // AWS S3 & MinIO
    implementation("io.minio:minio:8.5.7")

    // Mail
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")
    implementation("org.eclipse.angus:jakarta.mail:2.0.3")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.awaitility:awaitility:4.2.1")

    // Database & Cache Tests
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-redis-test")
    testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")

    // Messaging & Security Tests
    testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-client-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")


}

tasks.withType<Test> {
    useJUnitPlatform()
}