package de.felixlf.koinannotationskmpsample.entity.repository

import arrow.core.Either
import de.felixlf.koinannotationskmpsample.entity.error.FileError
import de.felixlf.koinannotationskmpsample.entity.model.FileSource

/**
 * Interface for reading file contents.
 * Platform-specific implementations will use Okio's FileSystem
 * to provide consistent file reading across all platforms.
 */
interface FileReader {
    /**
     * Reads the entire content of a text file as a string.
     *
     * @param fileSource The file to read
     * @return Either a FileError or the file content as string
     */
    suspend fun readTextFile(fileSource: FileSource): Either<FileError, String>

    /**
     * Reads file as bytes.
     * Useful for binary files or when encoding needs to be handled separately.
     *
     * @param fileSource The file to read
     * @return Either a FileError or the file content as ByteArray
     */
    suspend fun readBytes(fileSource: FileSource): Either<FileError, ByteArray>
}
