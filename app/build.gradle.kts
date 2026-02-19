plugins {
    id("app.android.application")
    id("app.android.application.compose")
    id("app.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "app.web.drjackycv.mvvmtemplate"

    defaultConfig {
        applicationId = "app.web.drjackycv.mvvmtemplate"
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        javaCompileOptions.annotationProcessorOptions.arguments += mapOf(
            "room.schemaLocation" to "$projectDir/schemas"
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += setOf("META-INF/ui-tooling_release.kotlin_module")
        }
    }
}

dependencies {
    implementation(projects.presentation)
    implementation(projects.data)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.multidex)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.java.inject)

    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.timber)
    implementation(libs.google.material)
    debugImplementation(libs.leakcanary)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    testImplementation(libs.junit)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.espresso.core)
}
