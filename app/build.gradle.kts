import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    buildFeatures {
        viewBinding = true
    }

    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        multiDexEnabled = true
        applicationId = "app.web.drjackycv.mvvmtemplate"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        javaCompileOptions.annotationProcessorOptions.arguments += mapOf(
            "room.schemaLocation" to "$projectDir/schemas"
        )
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
        }
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/ui-tooling_release.kotlin_module")
        }
    }
    //testOptions.unitTests.returnDefaultValues = true
    namespace = "app.web.drjackycv.mvvmtemplate"
    lint {
//        abortOnError = false
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.android.core.ktx)
    implementation(libs.multidex)
    implementation(libs.fragment.ktx)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.rx)
    implementation(libs.dataStore.preferences)
    implementation(libs.coroutines.android)
    //dependency injection
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.navigation.compose)
    kapt(libs.dagger.hilt.compiler)
//    kapt(libs.dagger.hilt.android.compiler)
//    kapt(libs.hilt.androidx.compiler)
    implementation(libs.java.inject)
    //network
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.logging.interceptor)
    //other
    implementation(libs.timber)
    implementation(libs.material)
    debugImplementation(libs.leak.canary)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    //test
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}