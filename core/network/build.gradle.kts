plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.app.kotlin.serialization)
}

android {
    namespace = "app.web.drjackycv.core.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://rickandmortyapi.com/api/" + "\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://rickandmortyapi.com/api/" + "\""
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}

dependencies {
    api(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.okhttp.logging.interceptor)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.rx.java)
    implementation(libs.timber)
}
