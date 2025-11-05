// Ktlint configuration for Kotlin Multiplatform projects
plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

ktlint {
    version.set("1.2.1")
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
    }
    filter {
        exclude("**/generated/**")
        exclude("**/buildkonfig/**")
        exclude { element ->
            element.file.path.contains("${File.separator}build${File.separator}")
        }
    }
}

// Exclude generated files from all ktlint check tasks
tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask>().configureEach {
    exclude { element ->
        element.file.path.contains("${File.separator}build${File.separator}buildkonfig${File.separator}") ||
        element.file.path.contains("${File.separator}build${File.separator}generated${File.separator}")
    }
}