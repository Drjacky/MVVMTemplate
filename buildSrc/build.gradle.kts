plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.2.0-alpha07")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0")
}