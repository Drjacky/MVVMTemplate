import dev.detekt.gradle.Detekt
import dev.detekt.gradle.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.gradleVersionsPlugin) apply false
    alias(libs.plugins.gradleDoctorPlugin) apply false
    alias(libs.plugins.dependencyAnalysis) apply false
    alias(libs.plugins.sonatypeScanGradle) apply false
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.compose.rules.detekt)
}

val baselineFile = file("$rootDir/config/detekt/baseline.xml")
val configFile = file("$rootDir/config/detekt/detekt.yml")

detekt {
    toolVersion.set(libs.versions.detekt.get())
    buildUponDefaultConfig.set(true)
    baseline.set(baselineFile)
    config.setFrom(configFile)
    parallel.set(true)
    reportsDir.set(file("$projectDir/build/detekt"))
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs Detekt analysis on the whole project."
    parallel.set(true)
    buildUponDefaultConfig.set(true)
    setSource(file(projectDir))
    config.setFrom(configFile)
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")
    baseline.set(baselineFile)
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats the whole project with Detekt."
    parallel.set(true)
    disableDefaultRuleSets.set(true)
    buildUponDefaultConfig.set(true)
    autoCorrect.set(true)
    setSource(file(projectDir))
    config.setFrom(configFile)
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")
    baseline.set(baselineFile)
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides the current Detekt baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(file(projectDir))
    config.setFrom(configFile)
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")
    baseline.set(baselineFile)
}
