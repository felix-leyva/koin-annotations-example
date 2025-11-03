dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    // Required to access libs catalog accessor. See: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}