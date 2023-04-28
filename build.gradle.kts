plugins {
    id("org.springframework.boot") version "2.7.11"
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
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.4"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.6")
    implementation("com.github.Steveice10:MCProtocolLib:1.18-3")
    implementation("net.kyori:adventure-text-serializer-legacy:4.13.1")
    implementation("com.google.guava:guava:31.1-jre")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
}


tasks {
    bootJar {
        archiveFileName.set("mcapi.jar")
    }
}
