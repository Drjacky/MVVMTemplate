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
        //useIR = true
    }
    sourceSets {
        map { it.java.srcDir("src/${it.name}/kotlin") }
    }
    buildTypes {
        named("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://rickandmortyapi.com/api/" + "\""
            )
        }
        named("release") {
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "BASE_URL",
                "\"" + "https://rickandmortyapi.com/api/" + "\""
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
    implementation(libs.kotlin)
    implementation(libs.android.core.ktx)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.rx)
    implementation(libs.rx.coroutine)
    implementation(libs.multidex)
    //dependency injection
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
//    kapt(libs.dagger.hilt.android.compiler)
//    kapt(libs.hilt.androidx.compiler)
//    implementation(libs.dagger.hilt.core)
    implementation(libs.java.inject)
    //parser
    implementation(libs.gson)
    api(libs.converter.gson)
    //network
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx)
    implementation(libs.logging.interceptor)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)
    //other
    implementation(libs.timber)
    implementation(libs.material)
    //test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)

    testImplementation(project(path = ":domain", configuration = "unitTestImplementation"))
    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}