plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {

    compileSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.androidCompileSdkVersion)

    defaultConfig {
        multiDexEnabled = true
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
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
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

val unitTestImplementation by configurations.creating
configurations["compileOnly"].extendsFrom(unitTestImplementation)
configurations["testImplementation"].extendsFrom(unitTestImplementation)

dependencies {
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.kotlin)
    //android
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_rx_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.lifecycle_livedata)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.multidex)
    //reactive
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.rx_kotlin)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.rx_java)
    //dependency injection
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.java_inject)
    //test
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.junit)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_core)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_inline)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_kotlin)
}