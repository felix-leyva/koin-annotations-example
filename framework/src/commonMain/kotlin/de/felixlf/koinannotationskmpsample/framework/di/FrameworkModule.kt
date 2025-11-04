package de.felixlf.koinannotationskmpsample.framework.di

import org.koin.core.annotation.Module

// We should add the *Module (from CommonMain) into the Native*Module, so that declarations inside the
// Native*Module take priority: then declarations inside specific Native*Module (targets) override those in *Module
@Module([FrameworkModule::class])
expect class NativeFrameworkModule()

@Module
class FrameworkModule
