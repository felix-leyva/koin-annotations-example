// Add the plugins id for KMP, KSP
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.google.devtools.ksp")
}

kotlin {
    // Define the targets, which you would like that ksp generates code to.
    // NOTE: Android should be declared in a different convention plugin
    jvm()
    iosArm64()
    iosSimulatorArm64()
    wasmJs {
        browser()
    }

    sourceSets.commonMain {
        // Makes sure, that the generated sources from KSP for commonMains, are accessible
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        dependencies {
            // Add our koin core dependencies
            implementation(projectLibs.koin.core)
            implementation(projectLibs.koin.annotations)
        }
    }
}

// Define for each target defined in the kotlin module, a dependency for ksp koin compiler
dependencies {
    listOf(
        "kspCommonMainMetadata",
        "kspJvm",
        "kspIosArm64",
        "kspIosSimulatorArm64",
        "kspWasmJs",
    ).forEach { add(it, projectLibs.koin.ksp.compiler) }
}

ksp {
    // Ensure a config check is activated, to detect configuration issues on compile time
    arg("KOIN_CONFIG_CHECK", "true")
}

// We need to make sure that the commonMain KSP source generation runs, whenever an specific KSP
// target is run. See: https://github.com/google/ksp/issues/963#issuecomment-2970973355
tasks.withType<com.google.devtools.ksp.gradle.KspAATask>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

// Ensure that any task processing commonMain sources runs after KSP generates code
// This fixes issues with ktlint, detekt, and other source processing tools that might
// try to process KSP-generated sources before they exist
tasks.configureEach {
    if (name.contains("CommonMainSourceSet", ignoreCase = true) && name != "kspCommonMainKotlinMetadata") {
        tasks.findByName("kspCommonMainKotlinMetadata")?.let { kspTask ->
            mustRunAfter(kspTask)
        }
    }
}
