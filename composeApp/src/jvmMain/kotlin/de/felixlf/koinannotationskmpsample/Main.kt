package de.felixlf.koinannotationskmpsample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.logger.Level
import org.koin.ksp.generated.startKoin

fun main() {
    KoinAnnotationsKMPSampleApp.startKoin {
        printLogger(Level.DEBUG)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KoinAnnotationsKMPSample",
        ) {
            App()
        }
    }
}
