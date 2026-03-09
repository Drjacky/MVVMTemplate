# MVVMTemplate 🧞‍

![Language](https://img.shields.io/badge/Kotlin-2.2.21-blue) ![AGP](https://img.shields.io/badge/AGP-9.0.1-orange) ![License](https://img.shields.io/github/license/Drjacky/MVVMTemplate?logo=MIT) [![Actions Status](https://github.com/Drjacky/MVVMTemplate/workflows/Pre%20Merge%20Checks/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Build Status](https://github.com/Drjacky/MVVMTemplate/workflows/Android%20CI/badge.svg)](https://github.com/Drjacky/MVVMTemplate/actions) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Drjacky_MVVMTemplate&metric=alert_status)](https://sonarcloud.io/dashboard?id=Drjacky_MVVMTemplate)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate?ref=badge_shield)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MVVMTemplate-green.svg?style=flat)](https://android-arsenal.com/details/1/8368) [![CodeScene Code Health](https://codescene.io/projects/29433/status-badges/code-health)](https://codescene.io/projects/29433)
<!-- [![Known Vulnerabilities](https://snyk.io/test/github/Drjacky/MVVMTemplate/badge.svg)](https://snyk.io/test/github/Drjacky/MVVMTemplate) Snyk doesn't support kotlin dsl -->

##

<p align="center">
  <img width="300px" src="https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/gif/logo.svg" />
</p>

A simple Android template that lets you create an **Android** project quickly.

## How to use 👣

Just click
on [![Use this template](https://img.shields.io/badge/-Use%20this%20template-brightgreen)](https://github.com/Drjacky/MVVMTemplate/generate)
button to create a new repo starting from this template.

## Architecture 🏗

This project
follows [Clean Architecture](https://proandroiddev.com/mvvm-with-clean-architecture-c2c021e05c89)
with MVVM and is organized into a **multi-module** structure:

```
MVVMTemplate/
├── app/                     # Application module (MainActivity, Navigation, DI wiring)
├── build-logic/             # Convention plugins (shared build configuration)
├── core/
│   ├── common/              # Shared base classes, utilities, DataStore
│   ├── data/                # Repository implementations, data sources, API models
│   ├── domain/              # Use cases, domain models, repository interfaces
│   ├── network/             # Retrofit setup, OkHttp configuration, network response handling
│   ├── testing/             # Shared test utilities (TestDispatcherRule, fakes, test data)
│   └── ui/                  # Shared Compose UI components, theming, screen states
└── feature/
    └── products/            # Products feature (list, detail, choose screens)
```

The app demonstrates **both RxJava and Coroutines** side by side - users choose which reactive
approach to explore at runtime via a choose screen.

## Features 🕹

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
  with [Material 3](https://m3.material.io/)
  and [Material You](https://material.io/blog/announcing-material-you) dynamic color
- [Multi-module architecture](https://developer.android.com/topic/modularization)
  with [convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
- [Version Catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) (
  `libs.versions.toml`) for centralized dependency management
- [KSP](https://developer.android.com/build/migrate-to-ksp) for annotation processing

### Reactive & Concurrency

- [Coroutines](https://developer.android.com/kotlin/coroutines) & [Flow](https://developer.android.com/kotlin/flow)
  with `StateFlow` for UI state
- [RxJava 3](https://github.com/ReactiveX/RxJava)

### Dependency Injection

- [Hilt](https://dagger.dev/hilt/) with KSP

### Networking & Serialization

- [Retrofit 3](https://github.com/square/retrofit) - HTTP client
- [OkHttp](https://github.com/square/okhttp) - Network interceptor
- [kotlinx-serialization](https://github.com/Kotlin/kotlinx.serialization) - JSON serialization
- Custom `NetworkResponse` sealed class for structured API response handling

### Navigation

- [Navigation 3](https://developer.android.com/develop/ui/compose/navigation) - Type-safe Compose
  navigation with `NavKey` and `NavDisplay`

### UI & Theming

- [Material 3](https://m3.material.io/) with dynamic color, light/dark theme, edge-to-edge support
- [Lottie](https://airbnb.design/lottie/) - Vector animations
- [Coil](https://coil-kt.github.io/coil/) - Image loading
- [Palette](https://developer.android.com/develop/ui/views/graphics/palette-colors) - Dynamic card
  coloring from image palettes
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -
  Pagination (both Coroutine and RxJava data sources)
- Compose stability configuration (`compose_compiler_config.conf`) and compiler metrics/reports

### Data

- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Preferences
  storage

### Quality & Tooling

- [Detekt](https://github.com/detekt/detekt) with Compose rules - Static code analysis
- [StrictMode](https://developer.android.com/reference/android/os/StrictMode) - Runtime guardrails (
  `detectAll()` thread policy, leaked object detection)
- [LeakCanary](https://square.github.io/leakcanary/) - Memory leak detection
- [Chucker](https://github.com/ChuckerTeam/chucker) - HTTP inspector
- [Timber](https://github.com/JakeWharton/timber) - Logging
- [Gradle Doctor](https://github.com/runningcode/gradle-doctor) - Build health
- [Dependency Analysis](https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin) -
  Dependency insights
- [Sonatype Scan](https://github.com/sonatype-nexus-community/scan-gradle-plugin) - Dependency
  security scanning

### Testing

- Unit tests
  with [Fakes over Mocks](https://testing.googleblog.com/2024/02/increase-test-fidelity-by-avoiding-mocks.html)
  pattern
- [Truth](https://truth.dev/) - Fluent assertions
- [Turbine](https://github.com/cashapp/turbine) - Flow testing
- [Coroutines Test](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/) -
  `TestDispatcherRule` for deterministic coroutine testing
- Shared `core:testing` module with reusable fakes and test data

### Accessibility & Internationalization

- Semantic properties for TalkBack (`mergeDescendants`, `heading`, `contentDescription`)
- Touch target sizing (48dp minimum)
- String resources with translator context comments
- Non-transitive R classes for proper resource scoping

## CI 🏭

This template uses [**GitHub Actions**](https://github.com/Drjacky/MVVMTemplate/actions) as CI.

Available workflows:

- [Validate Gradle Wrapper](.github/workflows/gradlew-validation.yml) - Checks the Gradle wrapper
  has a valid checksum
- [Pre Merge Checks](.github/workflows/pre-merge.yml) - Runs `build` and `testDebugUnitTest` tasks
- [Android](.github/workflows/android.yml) - Runs `assembleDebug` task

## Tasks 🔧

- `dependencyUpdates` - Displays the dependency updates for the project
- `detekt` - Runs static Kotlin code analysis for the whole project
- `buildHealth` - Provides advice for managing dependencies and applied plugins
- `ossIndexAudit` - Scans dependencies using Sonatype OSS Index for known vulnerabilities

## References 🧷

- [Rick and Morty API](https://rickandmortyapi.com/)
- [Right or Left animation by Marco Martina on LottieFiles](https://lottiefiles.com/21141-right-or-left)
- [Loading Beer animation by Hashim Irfan on LottieFiles](https://lottiefiles.com/30697-loading-beer-animation)

## Contributing 🤝

Feel free to open an issue or submit a pull request for any bugs/improvements.

## Result 📺
<img src="https://raw.githubusercontent.com/Drjacky/MVVMTemplate/master/gif/compose.gif" width="350px" height="700px" />

## License ⚖️
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate?ref=badge_large)