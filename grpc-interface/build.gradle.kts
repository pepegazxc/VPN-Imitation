plugins {
    java
    id("com.google.protobuf") version "0.9.4"
}

group = "me.pepega"
version = "0.0.1-SNAPSHOT"
description = "grpc-interface"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

val grpcVersion = "1.62.2"
val protobufVersion = "3.25.1"

dependencies {
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")

    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc") {
                    option("jakarta_omit=false")
                }
            }
        }
    }
}