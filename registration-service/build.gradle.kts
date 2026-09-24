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
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:4.0.4")

    implementation(project(":grpc-interface"))
    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter:0.3.0") {
        exclude(group = "io.grpc", module = "grpc-netty")
    }
    implementation("io.grpc:grpc-netty-shaded")

    // AWS S3 & MinIO
    implementation("io.minio:minio:8.6.0")

    // Mail
    implementation("org.springframework.boot:spring-boot-starter-mail")

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
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-client-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-resource-server-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")


}

tasks.withType<Test> {
    useJUnitPlatform()
}