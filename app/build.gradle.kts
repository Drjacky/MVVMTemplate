plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "app.web.drjackycv.mvvmtemplate"

    defaultConfig {
        applicationId = "app.web.drjackycv.mvvmtemplate"
        versionCode = 1
        versionName = "1.0.0"
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

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "DebugProbesKt.bin",
                "META-INF/rxjava.properties",
                "META-INF/proguard/androidx-annotations.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.androidx.dataStore.preferences)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.java.inject)

    implementation(libs.retrofit)
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
