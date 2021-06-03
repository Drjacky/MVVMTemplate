plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.15.0-RC1")
    //implementation("net.sf.proguard:proguard-gradle:6.2.2")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}