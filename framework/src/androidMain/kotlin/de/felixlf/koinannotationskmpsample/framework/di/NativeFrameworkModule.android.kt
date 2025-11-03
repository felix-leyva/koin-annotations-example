package de.felixlf.koinannotationskmpsample.framework.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module
@ComponentScan("de.felixlf.koinAnnotationskmpsample.framework")
@Configuration
actual class NativeFrameworkModule
