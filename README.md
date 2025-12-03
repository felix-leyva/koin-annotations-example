# Koin Annotations KMP Sample

A showcase project demonstrating how to configure **Koin with Annotations** in a **multi-module Kotlin Multiplatform** project, leveraging **convention plugins** for streamlined build management.

## Overview

This project illustrates modern dependency injection patterns in KMP using:

- **Koin Annotations**: Compile-time safe dependency injection with KSP (Kotlin Symbol Processing)
- **Multi-Module Architecture**: Clean separation of concerns across `domain`, `framework`, and presentation layers
- **Convention Plugins**: Centralized build configuration to reduce boilerplate across modules
- **Cross-Platform Support**: Targets Android, iOS, Web (Wasm/JS), and Desktop (JVM)

## Project Structure

```
koin-annotations-example/
├── domain/           # Core business entities and models (pure Kotlin)
├── framework/        # Platform implementations and infrastructure
├── composeApp/       # Presentation layer (Compose Multiplatform UI)
└── build-logic/      # Convention plugins for build configuration
```

### Key Features

1. **Convention Plugins**: All modules use custom Gradle convention plugins defined in `build-logic/` to apply consistent Koin and KSP configuration, eliminating repetitive build script code.

2. **Expect/Actual Pattern for DI**: Platform-specific implementations are injected through Koin modules, keeping business logic free from platform specifics.

3. **Compile-Time Safety**: KSP processes Koin annotations at compile time, catching configuration errors before runtime.

## Module Overview

* **[/composeApp](./composeApp/src)**: Shared Compose Multiplatform UI code
    - [commonMain](./composeApp/src/commonMain/kotlin): Common UI code for all platforms
    - Platform-specific folders (iosMain, jvmMain, etc.) for platform-specific implementations

* **[/domain](./domain/src)**: Core domain models and entities

* **[/framework](./framework/src)**: Infrastructure layer with platform-specific implementations

* **[/iosApp](./iosApp)**: iOS application entry point

* **[/build-logic](./build-logic)**: Convention plugins for centralized build configuration

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run
widget
in your IDE’s toolbar or build it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run
widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run Web Application

To build and run the development version of the web app, use the run configuration from the run
widget
in your IDE's toolbar or run it directly from the terminal:

- for the Wasm target (faster, modern browsers):
    - on macOS/Linux
      ```shell
      ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
      ```
    - on Windows
      ```shell
      .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
      ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run
widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

## Learn More

### About This Project
- [Detailed Architecture Guide](https://medium.com/@felix.lf/a-guide-to-modern-dependency-injection-in-kmp-with-koin-annotations-dcc086a976f3): Comprehensive guide on implementing Koin Annotations in KMP

### Technologies Used
- [Koin Documentation](https://insert-koin.io/): Dependency injection framework
- [Koin Annotations](https://insert-koin.io/docs/reference/koin-annotations/start): Compile-time DI with KSP
- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html): Cross-platform development
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform): Declarative UI framework
- [KSP (Kotlin Symbol Processing)](https://kotlinlang.org/docs/ksp-overview.html): Kotlin compiler plugin for code generation

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

This is a sample project for educational purposes demonstrating Koin Annotations in Kotlin Multiplatform.
