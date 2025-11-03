plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    // Required to access libs catalog accessor. See: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    // The plugins you will need in your convention plugin: ksp and kmp
    // Note these are the classpaths, not the plugin ids
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.com.google.devtools.ksp.gradle.plugin)
    // In case you also use android
    implementation(libs.android.gradle.plugin)
    // Ktlint plugin for code style checks
    implementation(libs.ktlint.gradle.plugin)
}
