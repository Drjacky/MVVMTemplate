import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
//    alias(libs.plugins.android.application) apply false
    id("org.sonarqube") version libs.versions.sonarqubeVersion.get()
    alias(libs.plugins.detekt)
    alias(libs.plugins.gradleVersionsPlugin) apply false
    alias(libs.plugins.gradleDoctorPlugin) apply false
    alias(libs.plugins.dependencyAnalysis) apply false
    alias(libs.plugins.sonatypeScanGradle) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://storage.googleapis.com/r8-releases/raw")
        }
    }
    dependencies {
//        classpath(libs.r8)
        classpath(libs.gradle)
        /*classpath(
            kotlin(
                libs.kotlin_gradle_plugin,
                version = app.web.drjackycv.buildsrc.Depends.Versions.kotlinVersion
            )
        )*/
        classpath(libs.navigation.safe.args.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.sonarqube.gradle.plugin)
    }
}

dependencies {
    detektPlugins(libs.detekt.gradle.plugin)
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.twitter.compose.rules)
    detektPlugins(libs.detekt.kode.compose.rules)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://oss.sonatype.org/content/repositories/snapshots") {
            mavenContent {
                snapshotsOnly()
            }
        }
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots") {
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}

val analysisDir = file(projectDir)
val baselineFile = file("$rootDir/config/detekt/baseline.xml")
val configFile = file("$rootDir/config/detekt/detekt.yml")
//val statisticsConfigFile = file("$rootDir/config/detekt/statistics.yml")

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

detekt {
    toolVersion = libs.versions.detektVersion.get()
    buildUponDefaultConfig = true
    baseline = baselineFile
    config = files("config/detekt/detekt.yml")
    config.from(files("config/detekt/detekt.yml"))
    config.setFrom(files(rootProject.file("config/detekt/detekt.yml")))
    source = objects.fileCollection().from(
        DetektExtension.DEFAULT_SRC_DIR_JAVA,
        "src/test/java",
        DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
        "src/test/kotlin"
    )
    reports {
        html.required.set(true)
        html.outputLocation.set(file("$projectDir/build/detekt/report.html"))
        xml.required.set(true)
        xml.outputLocation.set(file("$projectDir/build/detekt/report.xml"))
        txt.required.set(true)
        txt.outputLocation.set(file("$projectDir/build/detekt/report.txt"))
    }
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats whole project."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    setSource(analysisDir)
    config.from(files("config/detekt/detekt.yml"))
    config.setFrom(files(rootProject.file("config/detekt/detekt.yml")))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    baseline.set(baselineFile)
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
    }
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs the whole project at once."
    parallel = true
    buildUponDefaultConfig = true
    setSource(analysisDir)
    config.from(files("config/detekt/detekt.yml"))
    config.setFrom(files(rootProject.file("config/detekt/detekt.yml")))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    baseline.set(baselineFile)
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
    }
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(analysisDir)
    config.from(files("config/detekt/detekt.yml"))
    config.setFrom(files(rootProject.file("config/detekt/detekt.yml")))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    baseline.set(baselineFile)
}
