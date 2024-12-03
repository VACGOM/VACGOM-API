val activeProfile = System.getenv("PROFILE") ?: "product"
val imageTag = System.getenv("IMAGE_TAG") ?: "latest"
val repoURL: String? = System.getenv("IMAGE_REPO_URL")

plugins {
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
    id("io.freefair.lombok") version "8.10.2"
    id("com.google.cloud.tools.jib") version "3.4.3"

    kotlin("jvm") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
}

group = "kr.co.vacgom.api"
version = "3.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Kotlin specific
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.1")
    testImplementation("io.mockk:mockk:1.13.13")


    // Database
    runtimeOnly("com.mysql:mysql-connector-j")

    // OpenFeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.4")

    // Utilities
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-gson:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("com.auth0:java-jwt:4.4.0")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks {
    register<Copy>("initConfiguration") {
        from("./API-CONFIG")
        include("*.yml", "*.txt")
        into("./src/main/resources")
    }

    test {
        useJUnitPlatform()
    }

    processResources {
        dependsOn("initConfiguration")
    }
}

jib {
    from {
        image = "amazoncorretto:21-alpine-jdk"
    }
    to {
        image = repoURL
        tags = setOf(imageTag)
    }
    container {
        jvmFlags =
            listOf(
                "-Dspring.profiles.active=$activeProfile",
                "-Dserver.port=8080",
                "-XX:+UseContainerSupport",
                "-Duser.timezone=Asia/Seoul"
            )
        ports = listOf("8080")
    }
}
