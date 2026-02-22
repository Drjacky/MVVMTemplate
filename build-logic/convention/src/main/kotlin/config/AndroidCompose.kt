import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension,
) {
    commonExtension.buildFeatures.compose = true

    dependencies {
        val bom = libs.findLibrary("androidx.compose.bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
        add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        add("debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        val enableMetrics = project.providers.gradleProperty("enableComposeCompilerMetrics")
        if (enableMetrics.orNull.toBoolean()) {
            metricsDestination.set(project.layout.buildDirectory.dir("compose-metrics"))
        }

        val enableReports = project.providers.gradleProperty("enableComposeCompilerReports")
        if (enableReports.orNull.toBoolean()) {
            reportsDestination.set(project.layout.buildDirectory.dir("compose-reports"))
        }

        stabilityConfigurationFile.set(
            rootProject.layout.projectDirectory.file("compose_compiler_config.conf")
        )
    }
}
