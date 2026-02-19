plugins {
    id("app.android.library")
    id("app.hilt")
    id("kotlin-parcelize")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.rx.coroutine)
    implementation(libs.java.inject)

    implementation(libs.gson)
    api(libs.retrofit.converter.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.okhttp.logging.interceptor)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    implementation(libs.timber)
    implementation(libs.google.material)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))

    implementation(project(":domain"))
}
