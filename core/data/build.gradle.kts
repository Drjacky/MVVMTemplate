plugins {
    id("app.android.library")
    id("app.hilt")
    id("kotlin-parcelize")
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

    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)

    implementation(libs.timber)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(project(path = ":core:domain", configuration = "unitTestImplementation"))
}
