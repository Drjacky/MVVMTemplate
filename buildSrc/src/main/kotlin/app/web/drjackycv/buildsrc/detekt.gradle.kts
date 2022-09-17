import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("io.gitlab.arturbosch.detekt")
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
    toolVersion = app.web.drjackycv.buildsrc.Depends.Versions.detektVersion
    buildUponDefaultConfig = true
    baseline = baselineFile
    config = files("config/detekt/detekt.yml")
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

dependencies {
    detektPlugins(app.web.drjackycv.buildsrc.Depends.ClassPaths.detekt_gradle_plugin)
    detektPlugins(app.web.drjackycv.buildsrc.Depends.ClassPaths.detekt_twitter_compose_rules)
    detektPlugins(app.web.drjackycv.buildsrc.Depends.ClassPaths.detekt_kode_compose_rules)
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats whole project."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    setSource(analysisDir)
//    config.setFrom(listOf(statisticsConfigFile, configFile))
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
//    config.setFrom(listOf(statisticsConfigFile, configFile))
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
//    config.setFrom(listOf(statisticsConfigFile, configFile))
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    baseline.set(baselineFile)
}
