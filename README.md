# MVVMTemplate 🧞‍

![Language](https://img.shields.io/github/languages/top/Drjacky/MVVMTemplate?color=blue&logo=kotlin) ![License](https://img.shields.io/github/license/Drjacky/MVVMTemplate?logo=MIT) [![Actions Status](https://github.com/Drjacky/MVVMTemplate/workflows/Pre%20Merge%20Checks/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Build Status](https://github.com/Drjacky/MVVMTemplate/workflows/Android%20CI/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Drjacky_MVVMTemplate&metric=alert_status)](https://sonarcloud.io/dashboard?id=Drjacky_MVVMTemplate)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate?ref=badge_shield)
<!-- [![Known Vulnerabilities](https://snyk.io/test/github/Drjacky/MVVMTemplate/badge.svg)](https://snyk.io/test/github/Drjacky/MVVMTemplate) Snyk doesn't support kotlin dsl -->
A simple Android template that lets you create an **Android** project quickly. 

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/Drjacky/MVVMTemplate/generate) button to create a new repo starting from this template.

## Features 🕹

- 100% Kotlin-only template
- Following [Clean Architecture approach](https://proandroiddev.com/mvvm-with-clean-architecture-c2c021e05c89)
- Following MVVM Architectural Design Pattern
- Template Project
- [RxJava 2](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM
- [Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern library
- [Flow](https://developer.android.com/kotlin/flow) - Built on top of coroutines and is stream of data that can be computed asynchronously
- Simplest Adapter Ever (based on this [workaround](https://proandroiddev.com/the-best-android-recycler-adapter-youve-ever-seen-probably-177e25279a28))
- Github Actions - CI
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection framework
- [Transition](https://developer.android.com/guide/navigation/navigation-animate-transitions) - Animation
- [Paging V3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - Pagination
- [View Binding](https://developer.android.com/topic/libraries/view-binding) - View Binding
- [OkHttp3](https://github.com/square/okhttp) - Network interceptor
- [Retrofit](https://github.com/square/retrofit) - HTTP client
- [Glide](https://github.com/bumptech/glide) - Loading images
- [Timber](https://github.com/JakeWharton/timber) - Log
- [Gson](https://github.com/google/gson) - JSON library
- [Material Components](https://github.com/material-components/material-components-android) - Material Design
- [Lottie](https://airbnb.design/lottie/) - Vector animation library
- [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Alternative syntax to the Groovy DSL
- [Detekt](https://github.com/detekt/detekt) - Static code analysis for Kotlin
- [Gradle Doctor](https://github.com/runningcode/gradle-doctor) - Gradle build scan plugin
- [Navigation](https://developer.android.com/guide/navigation) - Navigate through the app
- [LeakCanary](https://square.github.io/leakcanary/) - Memory leak detection
- [Chucker](https://github.com/ChuckerTeam/chucker) - An HTTP inspector for Android & OkHTTP
- [StrictMode](https://developer.android.com/reference/android/os/StrictMode) - A developer tool which detects things you might be doing by accident
- [Dark/Light Theme](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme) - Support dark/light themes
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Data storage solution that allows you to store key-value pairs or typed objects
- [AutoDispose](https://uber.github.io/AutoDispose/) - Automatically binding the execution of RxJava streams to a provided scope

## Under Development 🚧

- ~~Add CI~~
- ~~Use Kotlin DSL~~
- Add Espresso, Instrumentation & Unit tests
- ~~Use Animations. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/2fc51ef6ff82c4d43168e3ae0124a30c4ec3bfff).~~
- ~~Use Hilt. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/b8af89bb74e5d615e9704c9f3ce35befd11811ea).~~
- ~~Migrate to [Hilt 2.31](https://github.com/google/dagger/releases/tag/dagger-2.31). To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/792d2ba54d214b3ef10862777fc386e7be5790f4).~~
- ~~Use Paging V3. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/pull/4/files).~~
- ~~Use ViewBinding. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/cfc907532fa991cd8de3b295644bfdff88d67ceb).~~
- Migrate to [JetPack Compose](https://developer.android.com/jetpack/compose)
- ~~Use detekt. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/pull/6/files).~~
- ~~Add coroutines and flow. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/pull/7/files).~~
- ~~Replace Preferences by DataStore. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/285892ce098e2a069324910a213b78cac2e643e8).~~

## CI 🏭

This template is using [**GitHub Actions**](https://github.com/Drjacky/MVVMTemplate/actions) as CI.

Available workflows listed as follows:
- [Validate Gradle Wrapper](.github/workflows/gradlew-validation.yml) - Checks the gradle wrapper has a valid checksum.
- [Pre Merge Checks](.github/workflows/pre-merge.yml) - Runs `build` task. 
- [Android](.github/workflows/android.yml) - Runs `assembleDebug` task.

## References 🧷

- [The Punk API](https://punkapi.com/)
- [Right or Left animation by Marco Martina on LottieFiles](https://lottiefiles.com/21141-right-or-left)
- [Loading Beer animation by Hashim Irfan on LottieFiles](https://lottiefiles.com/30697-loading-beer-animation)

## Contributing 🤝

Feel free to open an issue or submit a pull request for any bugs/improvements.

## Result 📺
<img src="https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/path.gif" width="350px" height="700px" /> <img src="https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/list.gif" width="350px" height="700px" />

## License ⚖️
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate?ref=badge_large)