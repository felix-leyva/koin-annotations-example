package de.felixlf.koinannotationskmpsample.di

import de.felixlf.koinannotationskmpsample.entity.di.EntityModule
import de.felixlf.koinannotationskmpsample.framework.di.FrameworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module([FrameworkModule::class, EntityModule::class])
@ComponentScan("de.felixlf.koinannotationskmpsample")
@Configuration
class ComposeAppModule
