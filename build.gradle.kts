plugins {
    id("org.sonarqube") version "3.0"
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(app.web.drjackycv.buildsrc.Depends.ClassPaths.gradle)
        classpath(
            kotlin(
                app.web.drjackycv.buildsrc.Depends.ClassPaths.kotlin_gradle_plugin,
                version = app.web.drjackycv.buildsrc.Depends.Versions.kotlinVersion
            )
        )
        classpath(app.web.drjackycv.buildsrc.Depends.ClassPaths.navigation_safe_args_gradle_plugin)
        classpath(app.web.drjackycv.buildsrc.Depends.ClassPaths.hilt_android_gradle_plugin)
        classpath(app.web.drjackycv.buildsrc.Depends.ClassPaths.sonarqube_gradle_plugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}