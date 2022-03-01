package app.web.drjackycv.buildsrc

import app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode

object Depends {

    object Versions {
        const val appVersionCode = 1_000_000
        const val gradleVersion = "7.1.2"
        const val androidCompileSdkVersion = 31
        const val targetSdkVersion = 31
        const val minSdkVersion =
            24 //Set it back to 21 when https://developer.android.com/jetpack/androidx/releases/compose-runtime#1.0.0
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val kotlinVersion = "1.6.10"
        const val rxKotlinVersion = "3.0.1"
        const val rxAndroidVersion = "3.0.0"
        const val rxJavaVersion = "3.1.3"
        const val rxBinding = "4.0.0"
        const val retrofit2Version = "2.9.0"
        const val okhttpLoggingVersion = "5.0.0-alpha.5"
        const val chuckerVersion = "3.5.2"
        const val gsonVersion = "2.9.0"
        const val lifecycleVersion = "2.5.0-alpha03"
        const val constraintLayoutVersion = "2.1.3"
        const val supportVersion = "1.4.0-rc01"
        const val materialVersion = "1.6.0-alpha03"
        const val coreKtxVersion = "1.9.0-alpha01"
        const val navigationVersion = "2.5.0-alpha03"
        const val pagingVersion = "3.1.0"
        const val multidexVersion = "2.0.1"
        const val fragmentExtVersion = "1.5.0-alpha03"
        const val recyclerviewVersion = "1.3.0-alpha01"
        const val hiltVersion = "2.41"
        const val hiltNavigationComposeVersion = "1.0.0"
        const val javaxInjectVersion = "1"
        const val timberVersion = "5.0.1"
        const val lottieVersion = "4.2.0"
        const val paletteVersion = "1.0.0"
        const val coilComposeVersion = "2.0.0-alpha09"
        const val autoDispose = "2.1.1"
        const val dataStorePreferenceVersion = "1.0.0"
        const val composeVersion = "1.2.0-alpha04"
        const val composePagingVersion = "1.0.0-alpha14"
        const val composeNavigationVersion = "2.5.0-alpha03"
        const val composeTextVersion = "1.2.0-alpha04"
        const val activityComposeVersion = "1.5.0-alpha03"
        const val materialComposeThemeAdapter = "1.1.5"
        const val composeConstraintLayout = "1.0.0"
        const val accompanistPlaceholderVersion = "0.24.3-alpha"

        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "3.12.4"
        const val mockitoInlineVersion = "3.12.4"
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
        const val dagger = "com.google.dagger:dagger:${Versions.hiltVersion}"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.hiltVersion}"
        const val dagger_hilt_android = "com.google.dagger:hilt-android:${Versions.hiltVersion}"

        /*const val dagger_hilt_android_compiler =
            "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"*/
        /*const val hilt_androidx_compiler =
            "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"*/
        /*const val dagger_hilt_core = "com.google.dagger:hilt-core:${Versions.hiltVersion}"*/
        const val dagger_hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
        const val dagger_hilt_navigation_compose =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"
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
        const val lifecycle_viewmodel_runtime_ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val lifecycle_common_java8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
        const val lifecycle_viewmodel_ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
        const val rx_java_android = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroidVersion}"
        const val rx_binding3 = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
        const val coil_compose = "io.coil-kt:coil-compose:${Versions.coilComposeVersion}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
        const val palette = "androidx.palette:palette:${Versions.paletteVersion}"
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
        const val compose_foundation =
            "androidx.compose.foundation:foundation:${Versions.composeVersion}"
        const val compose_foundation_layout =
            "androidx.compose.foundation:foundation-layout:${Versions.composeVersion}"
        const val compose_material = "androidx.compose.material:material:${Versions.composeVersion}"
        const val compose_runtime = "androidx.compose.runtime:runtime:${Versions.composeVersion}"
        const val compose_runtime_livedata =
            "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
        const val compose_runtime_saved_instance_state =
            "androidx.compose.runtime:runtime-saved-instance-state:${Versions.composeVersion}"
        const val compose_navigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
        const val compose_ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        const val compose_ui_text = "androidx.compose.ui:ui-text:${Versions.composeTextVersion}"
        const val compose_ui_test = "androidx.compose.ui:ui-test:${Versions.composeVersion}"
        const val compose_paging = "androidx.paging:paging-compose:${Versions.composePagingVersion}"
        const val compose_material_icons_extended =
            "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
        const val material_compose_theme_adapter =
            "com.google.android.material:compose-theme-adapter:${Versions.materialComposeThemeAdapter}"
        const val activity_compose =
            "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
        const val composeAnimationGraphics =
            "androidx.compose.animation:animation-graphics:${Versions.composeVersion}"
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
        const val accompanistPlaceholder =
            "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanistPlaceholderVersion}"
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