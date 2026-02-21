plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "app.web.drjackycv.core.domain"
}

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.java.inject)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)

    implementation(libs.rx.kotlin)
    implementation(libs.rx.java)

    testImplementation(project(":core:testing"))
}
