import app.web.drjackycv.buildsrc.Depends
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {

    compileSdk = Depends.Versions.androidCompileSdkVersion

    defaultConfig {
        multiDexEnabled = true
        minSdk = Depends.Versions.minSdkVersion
        targetSdk = Depends.Versions.targetSdkVersion
        testInstrumentationRunner =
            Depends.Libraries.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    project.tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
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
    namespace = "app.web.drjackycv.domain"

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
    //reactive
    implementation(Depends.Libraries.rx_kotlin)
    implementation(Depends.Libraries.rx_java)
    //dependency injection
    implementation(Depends.Libraries.dagger)
    kapt(Depends.Libraries.dagger_compiler)
    implementation(Depends.Libraries.dagger_hilt_android)
//    implementation(Depends.Libraries.dagger_hilt_core)
    kapt(Depends.Libraries.dagger_hilt_compiler)
//    kapt(Depends.Libraries.dagger_hilt_android_compiler)
//    kapt(Depends.Libraries.hilt_androidx_compiler)
    implementation(Depends.Libraries.java_inject)
    //test
    testImplementation(Depends.Libraries.junit)
    testImplementation(Depends.Libraries.mockito_core)
    testImplementation(Depends.Libraries.mockito_inline)
    testImplementation(Depends.Libraries.coroutines_test)
    testImplementation(Depends.Libraries.mockito_kotlin)
    testImplementation(Depends.Libraries.mockk)
    testImplementation(Depends.Libraries.arch_core_testing)
}