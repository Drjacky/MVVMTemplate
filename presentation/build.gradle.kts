import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    buildFeatures {
        compose = true
        viewBinding = true
    }
    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") { }
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    namespace = "app.web.drjackycv.presentation"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = listOf(
            "-Xskip-prerelease-check",
            //"-Xallow-jvm-ir-dependencies",
            "-Xopt-in=kotlin.RequiresOptIn",
        )
    }
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.kotlin.reflect)

    //dependency injection
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.navigation.compose)
//    kapt(libs.dagger.hilt.android.compiler)
//    implementation(libs.dagger.hilt.core)
    kapt(libs.dagger.hilt.compiler)
//    kapt(libs.dagger.hilt.android.compiler)
//    kapt(libs.hilt.androidx.compiler)
    implementation(libs.java.inject)
    //other
    implementation(libs.timber)
    //android
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.rx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.common.java8)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.multidex)
    implementation(libs.android.core.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.recyclerview)
    implementation(libs.dataStore.preferences)
    implementation(libs.coroutines.android)
    //compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.text)
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.paging)
    implementation(libs.compose.navigation)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.composeAnimationGraphics)
    implementation(libs.composeConstraintLayout)
    implementation(libs.accompanistPlaceholder)
//    implementation(libs.compose.runtime.saved.instance.state)
//    implementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.customview) //No more need for these two dependencies on Dolphin IDE: https://issuetracker.google.com/issues/227767363
    debugImplementation(libs.customview.poolingcontainer) //And this
    implementation(libs.material.compose.theme.adapter)
    implementation(libs.activity.compose)
    //reactive
    implementation(libs.rx.java.android)
    implementation(libs.rx.binding3)
    implementation(libs.rx.kotlin)
    implementation(libs.autodispose)
    implementation(libs.autodispose.android)
    implementation(libs.autodispose.android.arch)
    //ui
    implementation(libs.coil.compose)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    implementation(libs.palette)
    //test
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}
