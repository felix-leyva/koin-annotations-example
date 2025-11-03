@file:OptIn(KspExperimental::class)

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.google.devtools.ksp.KspExperimental

plugins {
    id("koin-kmp-android-lib-convention")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildkonfig)
    id("ktlint-convention")
}

kotlin {
    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.activity.ktx)
        }
        commonMain.dependencies {
            implementation(projects.domain)

            // File I/O
            implementation(libs.kotlinx.io)
            implementation(libs.kotlinx.bytestring)
            implementation(libs.filekit.core)
            implementation(libs.filekit.dialogs.compose)
            implementation(libs.filekit.dialogs)
            implementation(libs.filekit.coil)

            // Ktor Client
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.client.logging)

            // Arrow
            implementation(libs.arrow.core)
            implementation(libs.arrow.fx.coroutines)

            // Kotlinx Serialization
            implementation(libs.kotlinx.serialization.json)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
        }

        jvmMain.dependencies {
            // Ktor CIO engine for JVM
            implementation(libs.ktor.client.cio)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.koin.test)
        }
    }
}

android {
    namespace = "de.felixlf.koinannotationskmpsample.framework"
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
    packageName = "de.felixlf.koinannotationskmpsample.framework"

    defaultConfigs {
        // TTS API Configuration
        buildConfigField(
            STRING,
            "TTS_API_BASE_URL",
            System.getenv("TTS_API_BASE_URL") ?: "",
        )
        buildConfigField(
            STRING,
            "TTS_API_KEY",
            System.getenv("TTS_API_KEY") ?: "",
        )
        buildConfigField(
            STRING,
            "TTS_DEFAULT_MODEL",
            System.getenv("TTS_DEFAULT_MODEL") ?: "tts-1",
        )
        buildConfigField(
            STRING,
            "TTS_DEFAULT_VOICE",
            System.getenv("TTS_DEFAULT_VOICE") ?: "alloy",
        )
    }
}
