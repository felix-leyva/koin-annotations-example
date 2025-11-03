package de.felixlf.koinannotationskmpsample.framework.file

import de.felixlf.koinannotationskmpsample.entity.model.FileSource
import io.github.vinceglb.filekit.PlatformFile

interface PlatformFileConvertor {
    suspend fun convertPlatformFileToFileSource(platformFile: PlatformFile): FileSource
}
