import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
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
    detektPlugins(libs.detekt.twitter.compose.rules)
    detektPlugins(libs.detekt.kode.compose.rules)
}

val baselineFile = file("$rootDir/config/detekt/baseline.xml")
val configFile = file("$rootDir/config/detekt/detekt.yml")

detekt {
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
    baseline = baselineFile
    config.setFrom(configFile)
    parallel = true
    reports {
        html.required.set(true)
        html.outputLocation.set(file("$projectDir/build/detekt/report.html"))
        xml.required.set(true)
        xml.outputLocation.set(file("$projectDir/build/detekt/report.xml"))
    }
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs Detekt analysis on the whole project."
    parallel = true
    buildUponDefaultConfig = true
    setSource(file(projectDir))
    config.setFrom(configFile)
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")
    baseline.set(baselineFile)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats the whole project with Detekt."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
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
