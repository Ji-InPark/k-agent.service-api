import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
    kotlin("kapt") version "1.7.20"
}

allOpen {
    annotations(
        "jakarta.persistence.Entity",
        "jakarta.persistence.MappedSuperclass",
        "jakarta.persistence.Embedabble"
    )
}

group = "com.example"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jsoup:jsoup:1.15.3")
    implementation("org.springdoc", "springdoc-openapi-kotlin", "1.6.9")
    implementation("org.springdoc", "springdoc-openapi-ui", "1.6.9")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.flywaydb:flyway-core:9.16.3")
    implementation("org.springframework.boot:spring-boot-starter-security:3.1.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
