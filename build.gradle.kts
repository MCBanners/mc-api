plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}

group = "com.mcbanners"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
}

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2022.0.2"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.6")
    implementation("com.github.Steveice10:MCProtocolLib:1.19.4-1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.13.1")
    implementation("com.google.guava:guava:31.1-jre")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks {
    bootJar {
        archiveFileName.set("mcapi.jar")
    }
}
