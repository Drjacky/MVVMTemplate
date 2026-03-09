plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.navigationSafeArgs) apply false
    alias(libs.plugins.gradleVersionsPlugin) apply false
    alias(libs.plugins.gradleDoctorPlugin) apply false
    alias(libs.plugins.dependencyAnalysis) apply false
    alias(libs.plugins.sonatypeScanGradle) apply false
}
