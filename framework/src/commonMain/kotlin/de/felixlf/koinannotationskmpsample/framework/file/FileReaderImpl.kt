package de.felixlf.koinannotationskmpsample.framework.file

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.felixlf.koinannotationskmpsample.entity.error.FileError
import de.felixlf.koinannotationskmpsample.entity.model.FileSource
import de.felixlf.koinannotationskmpsample.entity.repository.FileReader
import io.ktor.utils.io.core.readText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.IOException
import kotlinx.io.buffered
import kotlinx.io.readByteArray
import org.koin.core.annotation.Single

/**
 * JVM implementation of FileReader using Okio.
 * Provides efficient file reading operations with proper resource management.
 */
@Single
class FileReaderImpl(private val readDispatcher: CoroutineDispatcher = Dispatchers.Default) : FileReader {
    override suspend fun readTextFile(fileSource: FileSource): Either<FileError, String> {
        return try {
            val content =
                withContext(readDispatcher) {
                    fileSource.rawSource.buffered().use {
                        it.readText()
                    }
                }
            if (content.isEmpty()) {
                FileError.EmptyFile(
                    message = "File is empty: ${fileSource.name}",
                ).left()
            } else {
                content.right()
            }
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to read file: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error reading file: ${e.message}",
                cause = e,
            ).left()
        }
    }

    override suspend fun readBytes(fileSource: FileSource): Either<FileError, ByteArray> {
        return try {
            val bytes = fileSource.rawSource.buffered().readByteArray()

            if (bytes.isEmpty()) {
                FileError.EmptyFile(
                    message = "File is empty: ${fileSource.name}",
                ).left()
            } else {
                bytes.right()
            }
        } catch (e: IOException) {
            FileError.ReadError(
                message = "Failed to read file bytes: ${e.message}",
                cause = e,
            ).left()
        } catch (e: Exception) {
            FileError.Unknown(
                message = "Unexpected error reading file: ${e.message}",
                cause = e,
            ).left()
        }
    }
}
