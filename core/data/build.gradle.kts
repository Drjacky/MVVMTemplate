plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.app.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.web.drjackycv.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.rx.coroutine)
    implementation(libs.java.inject)

    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)

    implementation(libs.timber)

    testImplementation(project(":core:testing"))
}
