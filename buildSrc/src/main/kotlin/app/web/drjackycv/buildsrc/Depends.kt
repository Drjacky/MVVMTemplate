package app.web.drjackycv.buildsrc

import app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode

object Depends {

    object Versions {
        const val appVersionCode = 1_000_000
        const val gradleVersion = "7.2.0-alpha07"
        const val androidCompileSdkVersion = 31
        const val targetSdkVersion = 31
        const val minSdkVersion = 21
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val kotlinVersion = "1.6.10"
        const val rxKotlinVersion = "3.0.1"
        const val rxAndroidVersion = "3.0.0"
        const val rxJavaVersion = "3.1.3"
        const val rxBinding = "4.0.0"
        const val retrofit2Version = "2.9.0"
        const val okhttpLoggingVersion = "5.0.0-alpha.3"
        const val chuckerVersion = "3.5.2"
        const val gsonVersion = "2.9.0"
        const val lifecycleVersion = "2.4.0"
        const val constraintLayoutVersion = "2.1.3"
        const val supportVersion = "1.4.1"
        const val materialVersion = "1.4.0"
        const val coreKtxVersion = "1.7.0"
        const val navigationVersion = "2.3.5"
        const val pagingVersion = "3.1.0"
        const val multidexVersion = "2.0.1"
        const val fragmentExtVersion = "1.4.0"
        const val recyclerviewVersion = "1.2.1"
        const val hiltVersion = "2.40.1"
        const val hiltCompilerVersion = "1.0.0"
        const val hiltNavigationComposeVersion = "1.0.0-alpha03"
        const val javaxInjectVersion = "1"
        const val timberVersion = "5.0.1"
        const val lottieVersion = "4.2.2"
        const val glideVersion = "4.13.0"
        const val autoDispose = "2.1.1"
        const val dataStorePreferenceVersion = "1.0.0"

        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "4.3.1"
        const val mockitoInlineVersion = "4.3.1"
        const val espressoVersion = "3.4.0"
        const val junitVersion = "4.13.2"
        const val supportTestVersion = "1.4.0"
        const val testCoreVersion = "1.4.0"
        const val testExtJunitVersion = "1.1.3"
        const val sonarqubeVersion = "3.3"
        const val detektVersion = "1.19.0"
        const val checkDependencyVersionsVersion = "0.42.0"
        const val gradleDoctorVersion = "0.8.0"
        const val leakCanaryVersion = "2.8.1"
        const val coroutinesTestVersion = "1.6.0"
        const val mockkVersion = "1.12.2"
        const val archCoreTestingVersion = "2.1.0"
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
        const val detekt_gradle_plugin =
            "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektVersion}"
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
        const val hilt_compiler =
            "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"
        const val fragment_ktx =
            "androidx.fragment:fragment-ktx:${Versions.fragmentExtVersion}"
        const val android_core_ktx =
            "androidx.core:core-ktx:${Versions.coreKtxVersion}"
        const val paging_runtime_ktx =
            "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
        const val paging_rx =
            "androidx.paging:paging-rxjava3:${Versions.pagingVersion}"
        const val java_inject = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
        const val retrofit_adapter_rx =
            "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit2Version}"
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
        const val coroutines_test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"
        const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
        const val arch_core_testing =
            "androidx.arch.core:core-testing:${Versions.archCoreTestingVersion}"
        const val rx_kotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlinVersion}"
        const val rx_java = "io.reactivex.rxjava3:rxjava:${Versions.rxJavaVersion}"
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
        const val lifecycle_common =
            "androidx.lifecycle:lifecycle-common:${Versions.lifecycleVersion}"
        const val lifecycle_viewmodel_ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
        const val rx_java_android = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroidVersion}"
        const val rx_binding3 = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
        const val autodispose = "com.uber.autodispose2:autodispose:${Versions.autoDispose}"
        const val autodispose_android =
            "com.uber.autodispose2:autodispose-android:${Versions.autoDispose}"
        const val autodispose_android_arch =
            "com.uber.autodispose2:autodispose-androidx-lifecycle:${Versions.autoDispose}"
        const val leak_canary =
            "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryVersion}"
        const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
        const val chucker_no_op =
            "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"
        const val dataStore_preferences =
            "androidx.datastore:datastore-preferences:${Versions.dataStorePreferenceVersion}"
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