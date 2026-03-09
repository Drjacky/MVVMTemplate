plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.web.drjackycv.core.domain"
}

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.java.inject)

    implementation(libs.rx.kotlin)
    implementation(libs.rx.java)

    testImplementation(project(":core:testing"))
}
