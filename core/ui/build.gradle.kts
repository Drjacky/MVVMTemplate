plugins {
    id("app.android.library")
    id("app.android.library.compose")
    id("app.hilt")
}

android {
    namespace = "app.web.drjackycv.core.ui"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.animation.graphics)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.activity.compose)

    implementation(libs.accompanist.placeholder)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
}
