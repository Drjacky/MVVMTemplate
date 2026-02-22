plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.android.application.compose)
    alias(libs.plugins.app.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.app.kotlin.serialization)
    id("org.jetbrains.kotlin.plugin.parcelize")
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
                "META-INF/ui-tooling_release.kotlin_module",
                "META-INF/versions/9/OSGI-INF/MANIFEST.MF",
            )
        }
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.ui)
    implementation(projects.feature.products)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.rx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.rx.java)
    implementation(libs.rx.kotlin)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.java.inject)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.animation.graphics)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)

    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.timber)
    implementation(libs.google.material)
    debugImplementation(libs.leakcanary)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    testImplementation(project(":core:testing"))
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.espresso.core)
}
