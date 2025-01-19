plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("java")
}

group = "com.mcbanners"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
}

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.3"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    implementation("com.github.Steveice10:MCProtocolLib:1.20.2-1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.17.0")
    implementation("com.google.guava:guava:33.2.1-jre")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks {
    bootJar {
        archiveFileName.set("mcapi.jar")
    }
}
