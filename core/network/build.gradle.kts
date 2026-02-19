plugins {
    id("app.android.library")
    id("app.hilt")
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
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://rickandmortyapi.com/api/" + "\""
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.gson)
    api(libs.retrofit.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.okhttp.logging.interceptor)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.rx.java)
    implementation(libs.timber)
}
