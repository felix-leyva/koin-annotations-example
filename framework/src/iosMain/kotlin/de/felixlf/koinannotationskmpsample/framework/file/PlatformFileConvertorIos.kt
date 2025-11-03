package de.felixlf.koinannotationskmpsample.framework.file

import de.felixlf.koinannotationskmpsample.entity.model.FileSource
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.mimeType
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.size
import io.github.vinceglb.filekit.source
import org.koin.core.annotation.Single

@Single
class PlatformFileConvertorIos : PlatformFileConvertor {
    override suspend fun convertPlatformFileToFileSource(platformFile: PlatformFile): FileSource =
        with(platformFile) {
            FileSource(
                name = name,
                rawSource = source(),
                mimeType = mimeType()?.primaryType ?: "",
                size = size(),
            )
        }
}
