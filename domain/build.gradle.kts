plugins {
    id("app.android.library")
    id("app.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "app.web.drjackycv.domain"
}

val unitTestImplementation: Configuration by configurations.creating
configurations["compileOnly"].extendsFrom(unitTestImplementation)
configurations["testImplementation"].extendsFrom(unitTestImplementation)

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.java.inject)

    implementation(libs.rx.kotlin)
    implementation(libs.rx.java)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
}
