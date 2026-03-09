plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.web.drjackycv.data"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://api.punkapi.com/v2/" + "\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://api.punkapi.com/v2/" + "\""
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.java.inject)

    implementation(libs.gson)
    api(libs.retrofit.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.okhttp.logging.interceptor)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)

    implementation(libs.timber)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.arch.core.testing)
}
