plugins {
//    detekt
    id("org.sonarqube") version app.web.drjackycv.buildsrc.Depends.Versions.sonarqubeVersion
//    id("io.gitlab.arturbosch.detekt") version app.web.drjackycv.buildsrc.Depends.Versions.detektVersion
    id("io.gitlab.arturbosch.detekt")
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
//        classpath(app.web.drjackycv.buildsrc.Depends.ClassPaths.detekt_gradle_plugin)
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

//plugins.apply(app.web.drjackycv.buildsrc.Plugins.DETEKT)
//apply(from= "$rootDir/buildSrc/src/main/kotlin/app/web/drjackycv/buildsrc/detekt.gradle.kts")

/*detekt {
    failFast = true // fail build on any finding
    buildUponDefaultConfig = true // preconfigure defaults
    config = files("$projectDir/config/detekt/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
//    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt

    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        html.destination = file("$projectDir/build/detekt/report.html")
        xml.enabled = true // checkstyle like format mainly for integrations like Jenkins
        xml.destination = file("$projectDir/build/detekt/report.xml")
        txt.enabled = true // similar to the console output, contains issue signature to manually edit baseline files
        txt.destination = file("$projectDir/build/detekt/report.txt")
    }
}

tasks {
    withType<Detekt> {
        this.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}*/

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}