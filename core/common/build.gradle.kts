plugins {
    id("app.android.library")
    id("app.hilt")
}

android {
    namespace = "app.web.drjackycv.core.common"
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.java.inject)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.autodispose)
    implementation(libs.autodispose.android)
    implementation(libs.autodispose.android.arch)
}
