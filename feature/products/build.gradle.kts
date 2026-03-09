plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigationSafeArgs)
}

android {
    namespace = "app.web.drjackycv.feature.products"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:common"))

    implementation(libs.timber)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.material)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.java.inject)

    implementation(libs.rx.android)
    implementation(libs.rx.binding)
    implementation(libs.rx.kotlin)
    implementation(libs.autodispose)
    implementation(libs.autodispose.android)
    implementation(libs.autodispose.android.arch)

    implementation(libs.glide)
    implementation(libs.lottie)
    implementation(libs.androidx.palette)

    testImplementation(project(":core:testing"))
    testImplementation(libs.arch.core.testing)

    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
