plugins {
    id("koin-kmp-android-lib-convention")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildkonfig)
    id("ktlint-convention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Arrow for error handling
            implementation(libs.arrow.core)
            implementation(libs.arrow.fx.coroutines)

            // Kotlinx Serialization
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.kotlinx.io)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
android {
    namespace = "de.felixlf.koinannotationskmpsample.entity"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

buildkonfig {
    packageName = "de.felixlf.koinannotationskmpsample.entity"

    defaultConfigs {
    }
}
