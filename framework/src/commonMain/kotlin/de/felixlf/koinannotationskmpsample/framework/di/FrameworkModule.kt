package de.felixlf.koinannotationskmpsample.framework.di

import de.felixlf.koinannotationskmpsample.entity.repository.FileReader
import de.felixlf.koinannotationskmpsample.framework.file.FileReaderImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
expect class NativeFrameworkModule()

@Module([NativeFrameworkModule::class])
class FrameworkModule {
    @Single
    fun provideFileReaderImpl(): FileReader {
        return FileReaderImpl()
    }
}
