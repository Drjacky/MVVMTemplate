/*
 * JaCoCo configuration for Android modules
 * Enables coverage flags and test task instrumentation.
 *
 * Combined `create*CombinedCoverageReport` tasks are not registered here: eagerly resolving
 * `AndroidComponentsExtension` for variant wiring correlated with compileSdk 37 failing to
 * resolve `:compile*JavaWithJavac` (MissingValueException) on this toolchain.
 */

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

internal fun Project.configureJacoco(
    commonExtension: CommonExtension,
) {
    commonExtension.buildTypes.named("debug") {
        enableAndroidTestCoverage = true
        enableUnitTestCoverage = true
    }

    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }

    tasks.withType<Test>().configureEach {
        configure<org.gradle.testing.jacoco.plugins.JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true

            // Required for JDK 11+
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}
