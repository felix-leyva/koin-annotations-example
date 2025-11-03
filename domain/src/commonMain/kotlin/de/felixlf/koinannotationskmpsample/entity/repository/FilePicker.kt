package de.felixlf.koinannotationskmpsample.entity.repository

import arrow.core.Either
import de.felixlf.koinannotationskmpsample.entity.error.FileError
import de.felixlf.koinannotationskmpsample.entity.model.FileSource

/**
 * Interface for selecting files from the file system.
 * Platform-specific implementations will use native file pickers:
 * - Android: Intent with ACTION_OPEN_DOCUMENT
 * - iOS: UIDocumentPickerViewController
 * - Desktop: JFileChooser or native dialogs
 * - Web: HTML file input
 */
interface FilePicker {
    /**
     * Opens a file picker dialog to select a single file.
     *
     * @param allowedExtensions List of allowed file extensions (e.g., ["txt", "md", "pdf", "epub"])
     *                          If null or empty, all files are allowed
     * @param title Optional title for the picker dialog
     * @return Either a FileError or the selected FileSource
     */
    suspend fun pickFile(
        allowedExtensions: List<String>? = null,
        title: String? = null,
    ): Either<FileError, FileSource>

    /**
     * Opens a file picker dialog to select multiple files.
     *
     * @param allowedExtensions List of allowed file extensions
     * @param title Optional title for the picker dialog
     * @return Either a FileError or a list of selected FileSources
     */
    suspend fun pickMultipleFiles(
        allowedExtensions: List<String>? = null,
        title: String? = null,
    ): Either<FileError, List<FileSource>>
}
