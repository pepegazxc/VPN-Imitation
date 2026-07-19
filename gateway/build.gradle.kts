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

subprojects {
    repositories {
        mavenCentral()
    }

    if (name != "grpc-interface") {

        tasks.withType<JavaCompile> {
            options.release.set(21)
        }

        dependencies {
            // Базовый Spring
            add("implementation", "org.springframework.boot:spring-boot-starter")

            // Lombok
            add("compileOnly", "org.projectlombok:lombok")
            add("annotationProcessor", "org.projectlombok:lombok")
            add("testAnnotationProcessor", "org.projectlombok:lombok")
            add("testCompileOnly", "org.projectlombok:lombok")

            // Тесты
            add("testImplementation", "org.springframework.boot:spring-boot-starter-test")
            add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
            add("testImplementation", "org.awaitility:awaitility:4.2.1")

            // Безопасность и JWT
            add("implementation", "org.springframework.boot:spring-boot-starter-security")
            "add"("io.jsonwebtoken:jjwt-api:0.12.5")?.let { add("implementation", it) }
            add("runtimeOnly", "io.jsonwebtoken:jjwt-impl:0.12.6")
            add("runtimeOnly", "io.jsonwebtoken:jjwt-jackson:0.12.5")

            // Обход падения с Docker Compose: добавляем динамически через строковое имя конфигурации
            add("developmentOnly", "org.springframework.boot:spring-boot-docker-compose")
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}