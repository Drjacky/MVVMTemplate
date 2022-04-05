plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.6.20")
    implementation("com.android.tools.build:gradle:7.3.0-alpha07")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0")
}