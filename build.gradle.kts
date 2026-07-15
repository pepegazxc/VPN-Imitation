plugins {
    java
    id("org.springframework.boot") version "4.1.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

group = "me.pepega"
version = "0.0.1-SNAPSHOT"
description = "vpn-imitation"

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = false
}
tasks.withType<Jar> {
    enabled = false
}

configure(subprojects.filter { it.name != "grpc-interface" }) {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")

        // Lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")

        // Tests
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.awaitility:awaitility:4.2.1")

        // Docker
        "developmentOnly"("org.springframework.boot:spring-boot-docker-compose")

        // Security And JWT
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("io.jsonwebtoken:jjwt-api:0.12.5")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

        // Подключаем наши grpc-контракты к каждому спринг-сервису
        implementation(project(":grpc-interface"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}