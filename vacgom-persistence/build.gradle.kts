import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
}

repositories { mavenCentral() }

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.3.5")
}

tasks.withType<BootJar> {
    enabled = false
}
