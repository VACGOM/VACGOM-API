import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val activeProfile = System.getenv("PROFILE") ?: "product"
val imageTag = System.getenv("IMAGE_TAG") ?: "latest"
val repoURL: String? = System.getenv("IMAGE_REPO_URL")

plugins {
    id("org.springframework.boot") version "3.3.5"
    id("com.google.cloud.tools.jib") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.6"

    id("io.freefair.lombok") version "8.10"
    kotlin("plugin.lombok") version "2.0.20"

    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.allopen") version "2.0.21"
}

allprojects {
    group = "kr.co.vacgom"
    version = "3.0.0"
    repositories { mavenCentral() }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    val springBootVersion = "3.3.5"
    val jsonwebtokenVersion = "0.12.6"
    val javaJwtVersion = "4.4.0"

    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
    implementation("io.jsonwebtoken:jjwt-gson:$jsonwebtokenVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
    implementation("com.auth0:java-jwt:$javaJwtVersion")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
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
        jvmFlags = listOf(
            "-Dspring.profiles.active=$activeProfile",
            "-Dserver.port=8080",
            "-XX:+UseContainerSupport",
            "-Duser.timezone=Asia/Seoul"
        )
        ports = listOf("8080")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")


    repositories { mavenCentral() }

    dependencies {
        val springBootVersion = "3.3.5"
        val jsonwebtokenVersion = "0.12.6"
        val javaJwtVersion = "4.4.0"

        implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
        implementation("io.jsonwebtoken:jjwt-gson:$jsonwebtokenVersion")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
        implementation("com.auth0:java-jwt:$javaJwtVersion")
        runtimeOnly("com.mysql:mysql-connector-j")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }
}
