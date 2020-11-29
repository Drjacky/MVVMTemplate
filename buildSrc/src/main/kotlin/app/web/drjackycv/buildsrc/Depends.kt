package app.web.drjackycv.buildsrc

import app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode

object Depends {

    object Versions {
        const val appVersionCode = 1_000_000
        const val gradleVersion = "4.1.1"
        const val androidCompileSdkVersion = 29
        const val targetSdkVersion = 29
        const val minSdkVersion = 21
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val kotlinVersion = "1.4.20"
        const val rxKotlinVersion = "2.4.0"
        const val rxAndroidVersion = "2.1.1"
        const val rxJavaVersion = "2.2.20"
        const val rxBinding = "3.1.0"
        const val retrofit2Version = "2.9.0"
        const val okhttpLoggingVersion = "4.9.0"
        const val chuckVersion = "1.1.0"
        const val gsonVersion = "2.9.0"
        const val lifecycleVersion = "2.2.0"
        const val constraintLayoutVersion = "2.0.4"
        const val supportVersion = "1.2.0"
        const val materialVersion = "1.3.0-alpha03"
        const val designSupportVersion = "28.0.0"
        const val coreKtxVersion = "1.3.2"
        const val navigationVersion = "2.3.1"
        const val pagingVersion = "3.0.0-alpha09"//"2.1.2"
        const val multidexVersion = "2.0.1"
        const val fragmentExtVersion = "1.2.5"
        const val recyclerviewVersion = "1.2.0-alpha06"
        const val hiltVersion = "2.30-alpha"
        const val hiltExtVersion = "1.0.0-alpha02"
        const val javaxInjectVersion = "1"
        const val timberVersion = "4.7.1"
        const val lottieVersion = "3.5.0"
        const val glideVersion = "4.11.0"
        const val autoDispose = "1.4.0"

        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "3.6.0"
        const val mockitoInlineVersion = "3.6.0"
        const val espressoVersion = "3.2.0"
        const val junitVersion = "4.13"
        const val supportTestVersion = "1.2.0"
        const val testCoreVersion = "1.2.0"
        const val testExtJunitVersion = "1.1.1"
        const val sonarqubeVersion = "3.0"

    }

    object ClassPaths {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val kotlin_gradle_plugin = "gradle-plugin"
        const val navigation_safe_args_gradle_plugin =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"

        const val hilt_android_gradle_plugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
        const val sonarqube_gradle_plugin =
            "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Versions.sonarqubeVersion}"
    }

    object Libraries {
        const val kotlin =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
        const val kotlin_reflect =
            "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinVersion}"
        const val multidex = "androidx.multidex:multidex:${Versions.multidexVersion}"
        const val hilt_android =
            "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hilt_android_compiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        const val hilt_lifecycle_viewmodel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltExtVersion}"
        const val hilt_compiler =
            "androidx.hilt:hilt-compiler:${Versions.hiltExtVersion}"
        const val fragment_ktx =
            "androidx.fragment:fragment-ktx:${Versions.fragmentExtVersion}"
        const val android_core_ktx =
            "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        const val android_lifecycle_extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
        const val paging_runtime_ktx =
            "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
        const val paging_rx_ktx =
            "androidx.paging:paging-rxjava2-ktx:${Versions.pagingVersion}"
        const val java_inject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
        const val retrofit_adapter_rx =
            "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit2Version}"
        const val logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingVersion}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val test_runner = "androidx.test:runner:${Versions.supportTestVersion}"
        const val test_rules = "androidx.test:rules:${Versions.supportTestVersion}"
        const val test_core = "androidx.test:core:${Versions.testCoreVersion}"
        const val test_ext_junit = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.supportVersion}"
        const val mockito_core = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
        const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
        const val mockito_kotlin =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
        const val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
        const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        const val lifecycle_livedata =
            "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycleVersion}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val navigation_fragment_ktx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigation_ui_ktx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val lifecycle_livedata_ktx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val lifecycle_runtime_ktx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val lifecycle_common_java8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
        const val lifecycle_viewmodel_ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
        const val rx_java_android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
        const val rx_binding3 = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
        const val autodispose = "com.uber.autodispose:autodispose:${Versions.autoDispose}"
        const val autodispose_android =
            "com.uber.autodispose:autodispose-android:${Versions.autoDispose}"
        const val autodispose_android_arch =
            "com.uber.autodispose:autodispose-android-archcomponents:${Versions.autoDispose}"
    }

    object Environments {
        const val debugBaseUrl = "https://api.punkapi.com/v2/"
        const val releaseBaseUrl = "https://api.punkapi.com/v2/"
    }

    fun generateVersionName(): String {
        val patch: Int = appVersionCode.rem(1000)
        val minor: Int = (appVersionCode / 1000).rem(1000)
        val major: Int = (appVersionCode / 1000000).rem(1000)

        return "$major.$minor.$patch"
    }

}