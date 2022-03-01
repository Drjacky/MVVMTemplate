import app.web.drjackycv.buildsrc.Depends

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdk = Depends.Versions.androidCompileSdkVersion
    defaultConfig {
        multiDexEnabled = true
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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
        //useIR = true
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

val unitTestImplementation: Configuration by configurations.creating
configurations["compileOnly"].extendsFrom(unitTestImplementation)
configurations["testImplementation"].extendsFrom(unitTestImplementation)

dependencies {
    implementation(Depends.Libraries.kotlin)
    //android
    implementation(Depends.Libraries.paging_runtime_ktx)
    implementation(Depends.Libraries.paging_rx)
    implementation(Depends.Libraries.lifecycle_livedata)
    implementation(Depends.Libraries.multidex)
    //dependency injection
    implementation(Depends.Libraries.dagger)
    kapt(Depends.Libraries.dagger_compiler)
    implementation(Depends.Libraries.dagger_hilt_android)
//    implementation(Depends.Libraries.dagger_hilt_core)
    kapt(Depends.Libraries.dagger_hilt_compiler)
//    kapt(Depends.Libraries.dagger_hilt_android_compiler)
//    kapt(Depends.Libraries.hilt_androidx_compiler)
    implementation(Depends.Libraries.java_inject)
    //reactive
    implementation(Depends.Libraries.rx_kotlin)
    implementation(Depends.Libraries.rx_java)
    //dependency injection
    implementation(Depends.Libraries.java_inject)
    //test
    testImplementation(Depends.Libraries.junit)
    testImplementation(Depends.Libraries.mockito_core)
    testImplementation(Depends.Libraries.mockito_inline)
    testImplementation(Depends.Libraries.mockito_kotlin)
}

kapt {
    correctErrorTypes = true
}