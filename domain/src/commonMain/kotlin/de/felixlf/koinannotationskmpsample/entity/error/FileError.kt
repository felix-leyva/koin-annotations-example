package de.felixlf.koinannotationskmpsample.entity.error

/**
 * Sealed hierarchy representing all possible file operation errors.
 */
sealed class FileError {
    abstract val message: String

    /**
     * The file was not found at the specified path.
     */
    data class NotFound(
        override val message: String = "File not found",
    ) : FileError()

    /**
     * Permission denied to access the file.
     * Common on Android when storage permissions are not granted,
     * or iOS when file access is restricted.
     */
    data class PermissionDenied(
        override val message: String = "Permission denied to access file",
    ) : FileError()

    /**
     * The file format is not supported.
     */
    data class UnsupportedFormat(
        val actualFormat: String,
        override val message: String = "Unsupported file format: $actualFormat",
    ) : FileError()

    /**
     * Error reading the file content.
     */
    data class ReadError(
        override val message: String,
        val cause: Throwable? = null,
    ) : FileError()

    /**
     * The file is too large to process.
     */
    data class FileTooLarge(
        val fileSize: Long,
        val maxSize: Long,
        override val message: String = "File too large: $fileSize bytes (max: $maxSize bytes)",
    ) : FileError()

    /**
     * The file content is empty.
     */
    data class EmptyFile(
        override val message: String = "File is empty",
    ) : FileError()

    /**
     * File selection was cancelled by the user.
     * This is platform-specific behavior when using file pickers.
     */
    data class Cancelled(
        override val message: String = "File selection cancelled",
    ) : FileError()

    /**
     * Generic error for unexpected issues.
     */
    data class Unknown(
        override val message: String,
        val cause: Throwable? = null,
    ) : FileError()
}
