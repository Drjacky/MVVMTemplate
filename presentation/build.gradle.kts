import app.web.drjackycv.buildsrc.Depends

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        compose = true
        viewBinding = true
        composeOptions.kotlinCompilerExtensionVersion = Depends.Versions.composeVersion
        composeOptions.kotlinCompilerVersion = Depends.Versions.kotlinVersion
    }
    compileSdk = Depends.Versions.androidCompileSdkVersion

    defaultConfig {
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        minSdk = Depends.Versions.minSdkVersion
        targetSdk = Depends.Versions.targetSdkVersion
        testInstrumentationRunner =
            Depends.Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") { }
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = listOf(
            "-XXLanguage:+InlineClasses",
            "-Xskip-prerelease-check",
            //"-Xallow-jvm-ir-dependencies",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xuse-experimental=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi"
        )
        //useIR = true
    }
}

dependencies {
    implementation(Depends.Libraries.kotlin)
    implementation(Depends.Libraries.kotlin_reflect)

    //dependency injection
    implementation(Depends.Libraries.dagger)
    kapt(Depends.Libraries.dagger_compiler)
    implementation(Depends.Libraries.dagger_hilt_android)
    implementation(Depends.Libraries.dagger_hilt_navigation_compose)
//    kapt(Depends.Libraries.dagger_hilt_android_compiler)
//    implementation(Depends.Libraries.dagger_hilt_core)
    kapt(Depends.Libraries.dagger_hilt_compiler)
//    kapt(Depends.Libraries.dagger_hilt_android_compiler)
//    kapt(Depends.Libraries.hilt_androidx_compiler)
    implementation(Depends.Libraries.java_inject)
    //other
    implementation(Depends.Libraries.timber)
    //android
    implementation(Depends.Libraries.appcompat)
    implementation(Depends.Libraries.constraintlayout)
    implementation(Depends.Libraries.material)
    implementation(Depends.Libraries.navigation_fragment_ktx)
    implementation(Depends.Libraries.navigation_ui_ktx)
    implementation(Depends.Libraries.paging_runtime_ktx)
    implementation(Depends.Libraries.paging_rx)
    implementation(Depends.Libraries.lifecycle_livedata_ktx)
    implementation(Depends.Libraries.lifecycle_runtime_ktx)
    implementation(Depends.Libraries.lifecycle_viewmodel_runtime_ktx)
    implementation(Depends.Libraries.lifecycle_common_java8)
    implementation(Depends.Libraries.lifecycle_viewmodel_ktx)
    implementation(Depends.Libraries.multidex)
    implementation(Depends.Libraries.android_core_ktx)
    implementation(Depends.Libraries.fragment_ktx)
    implementation(Depends.Libraries.recyclerview)
    implementation(Depends.Libraries.dataStore_preferences)
    //compose
    implementation(Depends.Libraries.compose_foundation)
    implementation(Depends.Libraries.compose_foundation_layout)
    implementation(Depends.Libraries.compose_ui)
    implementation(Depends.Libraries.compose_ui_text)
    implementation(Depends.Libraries.compose_material)
    implementation(Depends.Libraries.compose_runtime)
    implementation(Depends.Libraries.compose_runtime_livedata)
    implementation(Depends.Libraries.compose_paging)
    implementation(Depends.Libraries.compose_navigation)
    implementation(Depends.Libraries.compose_material_icons_extended)
    implementation(Depends.Libraries.composeAnimationGraphics)
    implementation(Depends.Libraries.composeConstraintLayout)
    implementation(Depends.Libraries.accompanistPlaceholder)
//    implementation(Depends.Libraries.compose_runtime_saved_instance_state)
//    implementation(Depends.Libraries.compose_ui_test)
    implementation(Depends.Libraries.compose_ui_tooling)
    implementation(Depends.Libraries.material_compose_theme_adapter)
    implementation(Depends.Libraries.activity_compose)
    //reactive
    implementation(Depends.Libraries.rx_java_android)
    implementation(Depends.Libraries.rx_binding3)
    implementation(Depends.Libraries.rx_kotlin)
    implementation(Depends.Libraries.autodispose)
    implementation(Depends.Libraries.autodispose_android)
    implementation(Depends.Libraries.autodispose_android_arch)
    //ui
    implementation(Depends.Libraries.coil_compose)
    implementation(Depends.Libraries.lottie)
    implementation(Depends.Libraries.palette)
    //test
    androidTestImplementation(Depends.Libraries.test_runner)
    androidTestImplementation(Depends.Libraries.test_rules)
    androidTestImplementation(Depends.Libraries.test_core)
    androidTestImplementation(Depends.Libraries.test_ext_junit)
    androidTestImplementation(Depends.Libraries.espresso_core)

    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}
