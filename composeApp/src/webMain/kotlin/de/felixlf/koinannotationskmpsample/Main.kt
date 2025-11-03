package de.felixlf.koinannotationskmpsample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.core.logger.Level
import org.koin.ksp.generated.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    KoinAnnotationsKMPSampleApp.startKoin {
        printLogger(Level.DEBUG)
    }
    ComposeViewport {
        App()
    }
}
