plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.5.20")
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.17.1")
}