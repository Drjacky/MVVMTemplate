plugins {
    id("app.android.library")
    id("app.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "app.web.drjackycv.core.domain"
}

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.java.inject)

    implementation(libs.rx.kotlin)
    implementation(libs.rx.java)

    testImplementation(project(":core:testing"))
}
