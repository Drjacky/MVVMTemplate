plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.androidCompileSdkVersion)

    defaultConfig {
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        minSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.minSdkVersion)
        targetSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.targetSdkVersion)
        versionCode = app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode
        versionName = app.web.drjackycv.buildsrc.Depends.generateVersionName()
        testInstrumentationRunner =
            app.web.drjackycv.buildsrc.Depends.Versions.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding {
        isEnabled = true
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

dependencies {
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.kotlin)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.kotlin_reflect)

    //dependency injection
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_android)
    kapt(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_android_compiler)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_lifecycle_viewmodel)
    kapt(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_compiler)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.java_inject)
    //other
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.timber)
    //android
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.appcompat)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.constraintlayout)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.material)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.navigation_fragment_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.navigation_ui_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_rx_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_lifecycle_extensions)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lifecycle_livedata_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lifecycle_runtime_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lifecycle_common_java8)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lifecycle_viewmodel_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.multidex)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.fragment_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.recyclerview)
    //reactive
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.rx_java_android)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.rx_binding3)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.rx_kotlin)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.autodispose)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.autodispose_android)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.autodispose_android_arch)
    //ui
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.glide)
    kapt(app.web.drjackycv.buildsrc.Depends.Libraries.glide_compiler)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lottie)
    //android architecture component
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_lifecycle_extensions)
    //test
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.test_runner)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.test_rules)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.test_core)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.test_ext_junit)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.espresso_core)

    implementation(project(":domain"))
}
