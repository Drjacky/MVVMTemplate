# MVVMTemplate 🧞‍

![Language](https://img.shields.io/github/languages/top/Drjacky/MVVMTemplate?color=blue&logo=kotlin) ![License](https://img.shields.io/github/license/Drjacky/MVVMTemplate?logo=MIT)

A simple Android template that lets you create an **Android** project quickly. 

## How to use 👣

Just click on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/Drjacky/MVVMTemplate/generate) button to create a new repo starting from this template.

## Features 🕹

- 100% Kotlin-only template
- Following Clean Architecture approach
- Following MVVM Architectural Design Pattern
- Pull Request Template
- Using Pagination
- Simplest Adapter Ever (based on this [workaround](https://proandroiddev.com/the-best-android-recycler-adapter-youve-ever-seen-probably-177e25279a28))
- Github Actions - CI

## Under Development 🚧

~~- Add CI~~
- Use Kotlin DSL
- Add Espresso, Instrumentation & Unit tests
- Use Animations

## CI ⚙️

This template is using [**GitHub Actions**](https://github.com/cortinico/kotlin-android-template/actions) as CI. You don't need to setup any external service and you should have a running CI once you start using this template.

There are currently the following workflows available:
- [Validate Gradle Wrapper](.github/workflows/gradle-wrapper-validation.yml) - Will check that the gradle wrapper has a valid checksum
- [Pre Merge Checks](.github/workflows/pre-merge.yaml) - Will run the `build`, `check` and `publishToMavenLocal` tasks. 

## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

## Result 📺
![Screenshot](https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/list.png)