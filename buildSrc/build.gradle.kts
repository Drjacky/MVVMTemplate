plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    gradlePluginPortal()
}
/*
kotlinDslPluginOptions {
    experimentalWarning.set(false)
}*/

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.15.0-RC1")
}