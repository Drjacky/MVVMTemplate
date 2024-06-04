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
    testOptions.unitTests.isReturnDefaultValues = true
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + Depends.Environments.debugBaseUrl + "\""
            )
        }
        named("release") {
            isMinifyEnabled = true
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + Depends.Environments.releaseBaseUrl + "\""
            )
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    namespace = "app.web.drjackycv.data"

}

dependencies {
    implementation(Depends.Libraries.kotlin)
    implementation(Depends.Libraries.android_core_ktx)
    implementation(Depends.Libraries.paging_runtime_ktx)
    implementation(Depends.Libraries.paging_rx)
    implementation(Depends.Libraries.multidex)
    //dependency injection
    implementation(Depends.Libraries.dagger)
    kapt(Depends.Libraries.dagger_compiler)
    implementation(Depends.Libraries.dagger_hilt_android)
    kapt(Depends.Libraries.dagger_hilt_compiler)
//    kapt(Depends.Libraries.dagger_hilt_android_compiler)
//    kapt(Depends.Libraries.hilt_androidx_compiler)
//    implementation(Depends.Libraries.dagger_hilt_core)
    implementation(Depends.Libraries.java_inject)
    //parser
    implementation(Depends.Libraries.gson)
    api(Depends.Libraries.converter_gson)
    //network
    implementation(Depends.Libraries.retrofit)
    implementation(Depends.Libraries.retrofit_adapter_rx)
    implementation(Depends.Libraries.logging_interceptor)
    debugImplementation(Depends.Libraries.chucker)
    releaseImplementation(Depends.Libraries.chucker_no_op)
    //other
    implementation(Depends.Libraries.timber)
    implementation(Depends.Libraries.material)
    //test
    testImplementation(Depends.Libraries.junit)
    testImplementation(Depends.Libraries.mockito_core)
    testImplementation(Depends.Libraries.mockito_inline)
    testImplementation(Depends.Libraries.mockito_kotlin)
    testImplementation(Depends.Libraries.mockk)
    testImplementation(Depends.Libraries.coroutines_test)
    testImplementation(Depends.Libraries.arch_core_testing)

    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    implementation(project(":domain"))
}