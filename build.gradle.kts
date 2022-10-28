plugins {
    id("org.sonarqube") version 3.5.0.2730
    detekt
    id("com.github.ben-manes.versions") version app.web.drjackycv.buildsrc.Depends.Versions.checkDependencyVersionsVersion
    id("com.osacky.doctor") version app.web.drjackycv.buildsrc.Depends.Versions.gradleDoctorVersion
    id("com.autonomousapps.dependency-analysis") version app.web.drjackycv.buildsrc.Depends.Versions.dependencyAnalysisVersion
    id("org.sonatype.gradle.plugins.scan") version app.web.drjackycv.buildsrc.Depends.Versions.sonatypeScanGradleVersion
}

buildscript {
    repositories {
        google()
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
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

configure<com.osacky.doctor.DoctorExtension> {
    disallowMultipleDaemons.set(false)
    negativeAvoidanceThreshold.set(500)
    warnWhenJetifierEnabled.set(true)

    javaHome {
        ensureJavaHomeIsSet.set(true)
        ensureJavaHomeMatches.set(true)
        failOnError.set(true)
    }
}
