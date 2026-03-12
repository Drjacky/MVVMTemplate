plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
}

android {
    namespace = "app.web.drjackycv.core.common"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.androidx.palette)
    implementation(libs.google.material)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.java.inject)

    implementation(libs.rx.java)
    implementation(libs.rx.android)
    implementation(libs.rx.binding)

    implementation(libs.glide)
    implementation(libs.timber)
}
