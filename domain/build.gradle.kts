plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {

    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()
    defaultConfig {
        multiDexEnabled = true
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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
    namespace = "app.web.drjackycv.domain"

}

val unitTestImplementation: Configuration by configurations.creating
configurations["compileOnly"].extendsFrom(unitTestImplementation)
configurations["testImplementation"].extendsFrom(unitTestImplementation)

dependencies {
    implementation(libs.kotlin)
    //android
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.rx)
    implementation(libs.lifecycle.livedata)
    implementation(libs.multidex)
    //dependency injection
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger.hilt.android)
//    implementation(libs.dagger.hilt.core)
    kapt(libs.dagger.hilt.compiler)
//    kapt(libs.dagger.hilt.android.compiler)
//    kapt(libs.hilt.androidx.compiler)
    implementation(libs.java.inject)
    //reactive
    implementation(libs.rx.kotlin)
    implementation(libs.rx.java)
    //dependency injection
    implementation(libs.java.inject)
    //test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
}

kapt {
    correctErrorTypes = true
}