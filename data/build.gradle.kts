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
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + app.web.drjackycv.buildsrc.Depends.Environments.debugBaseUrl + "\""
            )
        }
        named("release") {
            isMinifyEnabled = true
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + app.web.drjackycv.buildsrc.Depends.Environments.releaseBaseUrl + "\""
            )
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
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_rx_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.multidex)
    //dependency injection
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.java_inject)
    //parser
    api(app.web.drjackycv.buildsrc.Depends.Libraries.converter_gson)
    //network
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.retrofit)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.retrofit_adapter_rx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.logging_interceptor)
    //other
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.timber)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.material)
    //test
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.junit)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_core)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_inline)
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.mockito_kotlin)

    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    implementation(project(":domain"))
}