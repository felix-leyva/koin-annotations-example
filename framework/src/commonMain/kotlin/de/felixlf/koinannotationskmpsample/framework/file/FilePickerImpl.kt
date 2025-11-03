package de.felixlf.koinannotationskmpsample.framework.file

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import de.felixlf.koinannotationskmpsample.entity.error.FileError
import de.felixlf.koinannotationskmpsample.entity.model.FileSource
import de.felixlf.koinannotationskmpsample.entity.repository.FilePicker
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.withScopedAccess
import org.koin.core.annotation.Single

/**
 * JVM implementation of FilePicker using FileKit.
 * Uses native file chooser dialogs on Desktop platforms.
 *
 * TODO: Complete FileKit integration
 * - Import correct FileKit dependencies for JVM
 * - Use JFileChooser as fallback if FileKit not available
 * - Implement file picking with proper MIME type detection
 *
 * References:
 * - FileKit documentation: https://github.com/vinceglb/FileKit
 * - JFileChooser as fallback
 */
@Single
class FilePickerImpl(
    private val platformFileConvertor: PlatformFileConvertor,
) : FilePicker {
    override suspend fun pickFile(
        allowedExtensions: List<String>?,
        title: String?,
    ): Either<FileError, FileSource> =
        either {
            val file =
                ensureNotNull(FileKit.openFilePicker(mode = FileKitMode.Single)) {
                    FileError.Cancelled()
                }
            file.withScopedAccess {
                platformFileConvertor.convertPlatformFileToFileSource(it)
            }
        }

    override suspend fun pickMultipleFiles(
        allowedExtensions: List<String>?,
        title: String?,
    ): Either<FileError, List<FileSource>> =
        either {
            val files =
                ensureNotNull(FileKit.openFilePicker(mode = FileKitMode.Multiple())) {
                    FileError.Cancelled()
                }
            files.map { file ->
                file.withScopedAccess {
                    platformFileConvertor.convertPlatformFileToFileSource(it)
                }
            }
        }
}
