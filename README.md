# MVVMTemplate 🧞‍

![Language](https://img.shields.io/github/languages/top/Drjacky/MVVMTemplate?color=blue&logo=kotlin) ![License](https://img.shields.io/github/license/Drjacky/MVVMTemplate?logo=MIT) [![Actions Status](https://github.com/Drjacky/MVVMTemplate/workflows/Pre%20Merge%20Checks/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Build Status](https://github.com/Drjacky/MVVMTemplate/workflows/Android%20CI/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Known Vulnerabilities](https://snyk.io/test/github/Drjacky/MVVMTemplate/badge.svg)](https://snyk.io/test/github/Drjacky/MVVMTemplate) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Drjacky_MVVMTemplate&metric=alert_status)](https://sonarcloud.io/dashboard?id=Drjacky_MVVMTemplate)

A simple Android template that lets you create an **Android** project quickly. 

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/Drjacky/MVVMTemplate/generate) button to create a new repo starting from this template.

## Features 🕹

- 100% Kotlin-only template
- Following [Clean Architecture approach](https://proandroiddev.com/mvvm-with-clean-architecture-c2c021e05c89)
- Following MVVM Architectural Design Pattern
- Pull Request Template
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

## Under Development 🚧

- ~~Add CI~~
- Use Kotlin DSL
- Add Espresso, Instrumentation & Unit tests
- ~~Use Animations. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/2fc51ef6ff82c4d43168e3ae0124a30c4ec3bfff).~~
- ~~Use Hilt. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/b8af89bb74e5d615e9704c9f3ce35befd11811ea).~~
- ~~Use Paging V3. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/pull/4/files).~~
- ~~Use ViewBinding. To see changes, click [here](https://github.com/Drjacky/MVVMTemplate/commit/cfc907532fa991cd8de3b295644bfdff88d67ceb).~~

## CI 🏭

This template is using [**GitHub Actions**](https://github.com/Drjacky/MVVMTemplate/actions) as CI.

Available workflows listed as follows:
- [Validate Gradle Wrapper](.github/workflows/gradlew-validation.yml) - Checks the gradle wrapper has a valid checksum.
- [Pre Merge Checks](.github/workflows/pre-merge.yml) - Runs `build` task. 
- [Android](.github/workflows/android.yml) - Runs `assembleDebug` task. 

## Contributing 🤝

Feel free to open an issue or submit a pull request for any bugs/improvements.

## Result 📺
![Screenshot](https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/list.gif)