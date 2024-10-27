import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("io.freefair.lombok") version "8.10"
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.withType<BootJar> {
    enabled = false
}
