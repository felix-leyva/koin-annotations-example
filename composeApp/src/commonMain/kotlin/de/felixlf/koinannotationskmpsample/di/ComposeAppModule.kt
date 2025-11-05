package de.felixlf.koinannotationskmpsample.di

import de.felixlf.koinannotationskmpsample.entity.di.EntityModule
import de.felixlf.koinannotationskmpsample.framework.di.NativeFrameworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

// Include the Native*Modules, instead of the *Modules. As the Native*Modules should include themselves the *Modules, and
// therefore the Native*Modules would have priority by the "override" of declarations.
@Module([EntityModule::class, NativeFrameworkModule::class])
@ComponentScan("de.felixlf.koinannotationskmpsample")
@Configuration
class ComposeAppModule
