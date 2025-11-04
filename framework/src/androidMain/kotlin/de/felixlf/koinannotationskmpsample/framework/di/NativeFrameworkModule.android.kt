package de.felixlf.koinannotationskmpsample.framework.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module([FrameworkModule::class])
@ComponentScan("de.felixlf.koinAnnotationskmpsample.framework")
actual class NativeFrameworkModule
