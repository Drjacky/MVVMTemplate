package app.web.drjackycv.buildsrc

import app.web.drjackycv.buildsrc.Depends.Versions.appVersionCode

object Depends {

    object Versions {
        const val appVersionCode = 1_000_000
        const val r8Version = "8.1.56"
        const val gradleVersion = "8.2.0"
        const val androidCompileSdkVersion = 34
        const val targetSdkVersion = 34
        const val minSdkVersion = 21
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val kotlinVersion = "1.9.21"
        const val rxKotlinVersion = "3.0.1"
        const val rxAndroidVersion = "3.0.2"
        const val rxJavaVersion = "3.1.5"
        const val rxBinding = "4.0.0"
        const val rxCoroutine = "1.6.4"
        const val retrofit2Version = "2.9.0"
        const val okhttpLoggingVersion = "5.0.0-alpha.10"
        const val chuckerVersion = "3.5.2"
        const val gsonVersion = "2.10"
        const val retrofitConverterGson = "2.9.0"
        const val lifecycleVersion = "2.6.0-alpha01"
        const val constraintLayoutVersion = "2.1.4"
        const val supportVersion = "1.4.1"
        const val materialVersion = "1.6.0-alpha03"
        const val coreKtxVersion = "1.9.0"
        const val navigationVersion = "2.5.2"
        const val pagingVersion = "3.1.1"
        const val multidexVersion = "2.0.1"
        const val fragmentExtVersion = "1.5.0-alpha03"
        const val recyclerviewVersion = "1.3.0-rc01"
        const val hiltVersion = "2.47"
        const val hiltCompilerVersion = "1.0.0"
        const val hiltNavigationComposeVersion = "1.0.0-alpha03"
        const val javaxInjectVersion = "1"
        const val timberVersion = "5.0.1"
        const val lottieVersion = "5.2.0"
        const val paletteVersion = "1.0.0"
        const val coilComposeVersion = "2.2.1"
        const val autoDispose = "2.2.1"
        const val dataStorePreferenceVersion = "1.0.0"
        const val coroutinesAndroidVersion = "1.6.4"
        const val composeBomVersion = "2023.10.01"
        const val customViewPoolingContainerVersion = "1.0.0"
        const val customViewVersion = "1.2.0-alpha01"
        const val composeCompilerVersion = "1.5.6"
        const val composePagingVersion = "1.0.0-alpha16"
        const val composeNavigationVersion = "2.5.1"
        const val composeTextVersion = "1.3.0-beta01"
        const val activityComposeVersion = "1.6.0-rc01"
        const val materialComposeThemeAdapter = "1.1.17"
        const val composeConstraintLayout = "1.1.0-alpha03"
        const val accompanistPlaceholderVersion = "0.26.3-beta"
        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "4.10.0"
        const val mockitoInlineVersion = "4.10.0"
        const val espressoVersion = "3.5.0"
        const val junitVersion = "4.13.2"
        const val supportTestVersion = "1.5.0"
        const val testCoreVersion = "1.4.0"
        const val testExtJunitVersion = "1.1.3"
        const val sonarqubeVersion = "3.5.0.2730"
        const val detektVersion = "1.23.4"
        const val detektTwitterComposeRulesVersion = "0.0.18"
        const val detektKodeComposeRulesVersions = "1.2.2"
        const val checkDependencyVersionsVersion = "0.44.0"
        const val gradleDoctorVersion = "0.8.1"
        const val dependencyAnalysisVersion = "1.17.0"
        const val sonatypeScanGradleVersion = "2.5.3"
        const val leakCanaryVersion = "2.10"
        const val coroutinesTestVersion = "1.6.4"
        const val mockkVersion = "1.13.3"
        const val archCoreTestingVersion = "2.1.0"
    }

    object ClassPaths {
        const val r8 = "com.android.tools:r8:${Versions.r8Version}"
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
        const val detekt_twitter_compose_rules =
            "com.twitter.compose.rules:detekt:${Versions.detektTwitterComposeRulesVersion}"
        const val detekt_kode_compose_rules =
            "ru.kode:detekt-rules-compose:${Versions.detektKodeComposeRulesVersions}"
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
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val test_runner = "androidx.test:runner:${Versions.supportTestVersion}"
        const val test_rules = "androidx.test:rules:${Versions.supportTestVersion}"
        const val test_core = "androidx.test:core:${Versions.testCoreVersion}"
        const val test_ext_junit = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
        const val converter_gson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
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
        const val lifecycle_runtime_compose =
            "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleVersion}"
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
        const val rx_coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-rx3:${Versions.rxCoroutine}"
        const val coil_compose = "io.coil-kt:coil-compose:${Versions.coilComposeVersion}"
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
        const val lottie_compose = "com.airbnb.android:lottie-compose:${Versions.lottieVersion}"
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
        const val coroutines_android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
        const val compose_bom = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
        const val compose_foundation = "androidx.compose.foundation:foundation"
        const val compose_foundation_layout = "androidx.compose.foundation:foundation-layout"
        const val compose_material = "androidx.compose.material:material"
        const val compose_runtime = "androidx.compose.runtime:runtime"
        const val compose_runtime_livedata = "androidx.compose.runtime:runtime-livedata"
        const val compose_runtime_saved_instance_state = "androidx.compose.runtime:runtime-saveable"
        const val compose_navigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
        const val compose_ui = "androidx.compose.ui:ui"
        const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"
        const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
        const val customview = "androidx.customview:customview:${Versions.customViewVersion}"
        const val customview_poolingcontainer =
            "androidx.customview:customview-poolingcontainer:${Versions.customViewPoolingContainerVersion}"
        const val compose_ui_text = "androidx.compose.ui:ui-text"
        const val compose_ui_test = "androidx.compose.ui:ui-test"
        const val compose_paging = "androidx.paging:paging-compose:${Versions.composePagingVersion}"
        const val compose_material_icons_extended =
            "androidx.compose.material:material-icons-extended"
        const val material_compose_theme_adapter =
            "com.google.android.material:compose-theme-adapter:${Versions.materialComposeThemeAdapter}"
        const val activity_compose =
            "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
        const val composeAnimationGraphics = "androidx.compose.animation:animation-graphics"
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