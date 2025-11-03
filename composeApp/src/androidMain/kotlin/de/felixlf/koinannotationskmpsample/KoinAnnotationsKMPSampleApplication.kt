package de.felixlf.koinannotationskmpsample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.ksp.generated.startKoin

class KoinAnnotationsKMPSampleApplication : Application() {
    override fun onCreate() {
        KoinAnnotationsKMPSampleApp.startKoin {
            androidContext(this@KoinAnnotationsKMPSampleApplication)
            printLogger(Level.DEBUG)
        }
        super.onCreate()
    }
}
