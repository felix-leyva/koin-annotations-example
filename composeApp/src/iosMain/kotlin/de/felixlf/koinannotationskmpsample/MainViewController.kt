package de.felixlf.koinannotationskmpsample

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.logger.Level
import org.koin.ksp.generated.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    KoinAnnotationsKMPSampleApp.startKoin {
        printLogger(Level.DEBUG)
    }
    return ComposeUIViewController { App() }
}
