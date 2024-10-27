plugins {
    java
    id("io.freefair.lombok") version "8.10.2"
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    val uuidCreatorVersion = "6.0.0"
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")
}
