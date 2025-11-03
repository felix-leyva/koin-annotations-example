package de.felixlf.koinannotationskmpsample.framework.file

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.felixlf.koinannotationskmpsample.entity.error.FileError
import de.felixlf.koinannotationskmpsample.entity.repository.FileWriter
import io.github.vinceglb.filekit.utils.div
import io.github.vinceglb.filekit.utils.toPath
import kotlinx.io.IOException
import kotlinx.io.buffered
import kotlinx.io.files.FileSystem
import kotlinx.io.files.Path
import kotlinx.io.writeString
import org.koin.core.annotation.Single

/**
 * JVM implementation of FileWriter using Okio.
 * Provides efficient file writing operations with atomic operations where possible.
 */
@Single
class FileWriterImpl(
    private val fileSystem: FileSystem,
) : FileWriter {
    override suspend fun writeTextFile(
        directoryPath: String,
        fileName: String,
        content: String,
        overwrite: Boolean,
    ): Either<FileError, String> {
        return try {
            val dir = Path(directoryPath)
            val file = dir / fileName

            // Check if directory exists
            if (!fileSystem.exists(dir)) {
                return FileError.NotFound(
                    message = "Directory not found: $directoryPath",
                ).left()
            }

            // Check if file already exists and overwrite is false
            if (fileSystem.exists(file) && !overwrite) {
                return FileError.Unknown(
                    message = "File already exists: $fileName (use overwrite=true to replace)",
                ).left()
            }

            // Write file
            fileSystem.sink(file).buffered().use {
                it.writeString(content)
            }

            file.toString().right()
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to write file: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error writing file: ${e.message}",
                cause = e,
            ).left()
        }
    }

    override suspend fun writeBinaryFile(
        directoryPath: String,
        fileName: String,
        content: ByteArray,
        overwrite: Boolean,
    ): Either<FileError, String> {
        return try {
            val dir = directoryPath.toPath()
            val file = dir / fileName

            // Check if directory exists
            if (!fileSystem.exists(dir)) {
                return FileError.NotFound(
                    message = "Directory not found: $directoryPath",
                ).left()
            }

            // Check if file already exists and overwrite is false
            if (fileSystem.exists(file) && !overwrite) {
                return FileError.Unknown(
                    message = "File already exists: $fileName (use overwrite=true to replace)",
                ).left()
            }

            // Write file
            fileSystem.sink(file).buffered().use { sink ->
                sink.write(content)
            }

            file.toString().right()
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to write binary file: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error writing file: ${e.message}",
                cause = e,
            ).left()
        }
    }

    override suspend fun createDirectory(
        directoryPath: String,
        recursive: Boolean,
    ): Either<FileError, Unit> {
        return try {
            val dir = directoryPath.toPath()

            if (fileSystem.exists(dir)) {
                return Unit.right() // Directory already exists, consider it success
            }

            fileSystem.createDirectories(dir)

            Unit.right()
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to create directory: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error creating directory: ${e.message}",
                cause = e,
            ).left()
        }
    }

    override suspend fun deleteFile(filePath: String): Either<FileError, Unit> {
        return try {
            val file = filePath.toPath()

            if (!fileSystem.exists(file)) {
                return FileError.NotFound(
                    message = "File not found: $filePath",
                ).left()
            }

            fileSystem.delete(file)
            Unit.right()
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to delete file: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error deleting file: ${e.message}",
                cause = e,
            ).left()
        }
    }

    override suspend fun directoryExists(directoryPath: String): Boolean {
        return try {
            val dir = directoryPath.toPath()
            fileSystem.exists(dir) && fileSystem.metadataOrNull(dir)?.isDirectory == true
        } catch (e: Exception) {
            false
        }
    }
}
