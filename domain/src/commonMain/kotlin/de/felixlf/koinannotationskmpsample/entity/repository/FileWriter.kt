package de.felixlf.koinannotationskmpsample.entity.repository

import arrow.core.Either
import de.felixlf.koinannotationskmpsample.entity.error.FileError

/**
 * Interface for writing files to the file system.
 * Used for saving processed text chunks and generated audio files.
 */
interface FileWriter {
    /**
     * Writes text content to a file.
     *
     * @param directoryPath Path to the directory where file will be created
     * @param fileName Name of the file (with extension)
     * @param content Text content to write
     * @param overwrite If true, overwrites existing file. If false, returns error if file exists
     * @return Either a FileError or the path to the created file
     */
    suspend fun writeTextFile(
        directoryPath: String,
        fileName: String,
        content: String,
        overwrite: Boolean = false,
    ): Either<FileError, String>

    /**
     * Writes binary content to a file.
     *
     * @param directoryPath Path to the directory where file will be created
     * @param fileName Name of the file (with extension)
     * @param content Binary content to write
     * @param overwrite If true, overwrites existing file
     * @return Either a FileError or the path to the created file
     */
    suspend fun writeBinaryFile(
        directoryPath: String,
        fileName: String,
        content: ByteArray,
        overwrite: Boolean = false,
    ): Either<FileError, String>

    /**
     * Creates a directory if it doesn't exist.
     *
     * @param directoryPath Path to the directory to create
     * @param recursive If true, creates parent directories as needed
     * @return Either a FileError or Unit on success
     */
    suspend fun createDirectory(
        directoryPath: String,
        recursive: Boolean = true,
    ): Either<FileError, Unit>

    /**
     * Deletes a file.
     *
     * @param filePath Path to the file to delete
     * @return Either a FileError or Unit on success
     */
    suspend fun deleteFile(filePath: String): Either<FileError, Unit>

    /**
     * Checks if a directory exists.
     *
     * @param directoryPath Path to check
     * @return true if directory exists
     */
    suspend fun directoryExists(directoryPath: String): Boolean
}
