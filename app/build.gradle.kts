import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.androidCompileSdkVersion)

    dataBinding {
        isEnabled = true
    }

    defaultConfig {
        multiDexEnabled = true
        applicationId = "app.web.drjackycv.mvvmtemplate"
        minSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.minSdkVersion)
        targetSdkVersion(app.web.drjackycv.buildsrc.Depends.Versions.targetSdkVersion)
        versionCode = app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode
        versionName = app.web.drjackycv.buildsrc.Depends.generateVersionName()
        testInstrumentationRunner =
            app.web.drjackycv.buildsrc.Depends.Versions.testInstrumentationRunner
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
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    lintOptions {
        isAbortOnError = false
    }
    //testOptions.unitTests.returnDefaultValues = true
    packagingOptions {
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/proguard/androidx-annotations.pro")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}

dependencies {
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.kotlin)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_core_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.multidex)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.fragment_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.android_lifecycle_extensions)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_runtime_ktx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.paging_rx_ktx)
    //dependency injection
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_android)
    kapt(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_android_compiler)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_lifecycle_viewmodel)
    kapt(app.web.drjackycv.buildsrc.Depends.Libraries.hilt_compiler)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.java_inject)
    //network
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.retrofit)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.retrofit_adapter_rx)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.logging_interceptor)
    //other
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.timber)
    implementation(app.web.drjackycv.buildsrc.Depends.Libraries.material)
    //test
    testImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.junit)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.test_runner)
    androidTestImplementation(app.web.drjackycv.buildsrc.Depends.Libraries.espresso_core)

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
}