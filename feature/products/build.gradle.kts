plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.android.library.compose)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.app.kotlin.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "app.web.drjackycv.feature.products"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.java.inject)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.animation.graphics)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.activity.compose)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.rx.android)
    implementation(libs.rx.kotlin)

    implementation(libs.coil.compose)
    implementation(libs.androidx.palette)
    implementation(libs.timber)

    testImplementation(project(":core:testing"))
    testImplementation(libs.arch.core.testing)

    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
