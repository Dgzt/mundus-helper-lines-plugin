/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.0"

    kotlin("kapt") version "1.9.0"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    api("org.pf4j:pf4j:3.10.0")
    api("com.badlogicgames.gdx:gdx:1.12.0")
    kapt("org.pf4j:pf4j:3.10.0")
    api(files("../libs/commons-0.5.1.jar"))
    api(files("../libs/plugin-api-0.5.1.jar"))
    api(files("../libs/editor-commons-0.5.1.jar"))
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Plugin-Class"]= "com.dgzt.mundus.plugin.helperlines.HelperLinesPlugin"
        attributes["Plugin-Id"] = "helper-lines-plugin"
        attributes["Plugin-Provider"] = "Tibor Zsuro (Dgzt)"
        attributes["Plugin-Version"] = "0.0.1"
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
