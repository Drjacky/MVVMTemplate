plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "app.web.drjackycv.core.testing"
}

dependencies {
    implementation(project(":core:domain"))

    api(libs.junit)
    api(libs.truth)
    api(libs.turbine)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlin.test)

    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.kotlinx.coroutines.android)
}
