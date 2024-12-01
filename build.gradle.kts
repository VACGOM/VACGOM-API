import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("io.freefair.lombok") version "8.10.2"
}

dependencies {
    implementation(project(":vacgom-persistence"))

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework:spring-tx:6.1.14")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-security")

    //feign Client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    //uuid-creator
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")

    val javaJwtVersion = "4.4.0"
    implementation("com.auth0:java-jwt:$javaJwtVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    //kotest
    val kotestVersion = "5.5.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")

    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    val uuidCreatorVersion = "6.0.0"
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")

    //mockk
    val mockkVersion = "1.13.13"
    testImplementation("io.mockk:mockk:${mockkVersion}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}

tasks.processResources {
    dependsOn("initConfiguration")
}

tasks.register<Copy>("initConfiguration") {
    from("./API-CONFIG")
    include("*.yml", "*.txt")
    into("./src/main/resources")
}


tasks.withType<BootJar> {
    enabled = true
}
