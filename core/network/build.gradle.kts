plugins {
    id("app.android.library")
    id("app.hilt")
    alias(libs.plugins.kotlin.serialization)
    id("app.kotlin.serialization")
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
