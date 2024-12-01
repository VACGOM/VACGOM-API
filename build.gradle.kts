import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
    id("io.freefair.lombok") version "8.10.2"

    kotlin("jvm") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
}

group = "kr.co.vacgom.api"
version = "3.0.0"

sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/kotlin")
        resources.srcDir("src/main/resources")
    }
    test {
        java.srcDirs("src/test/java", "src/test/kotlin")
        resources.srcDir("src/test/resources")
    }
}


tasks.withType<JavaCompile> {
    sourceCompatibility = JvmTarget.JVM_21.target
    targetCompatibility = JvmTarget.JVM_21.target
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.4")

    implementation("com.github.f4b6a3:uuid-creator:6.0.0")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-gson:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("com.auth0:java-jwt:4.4.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.register<Copy>("initConfiguration") {
    from("./API-CONFIG")
    include("*.yml", "*.txt")
    into("./src/main/resources")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.processResources {
    dependsOn("initConfiguration")
}
