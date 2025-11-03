package de.felixlf.koinannotationskmpsample.entity.model

import kotlinx.io.RawSource

/**
 * Represents a file source that can be read.
 * This is a platform-agnostic representation of a file.
 *
 * @property name The name of the file (with extension)
 * @property mimeType MIME type of the file (e.g., "text/plain", "application/pdf")
 * @property rawSource a [RawSource] to perform read operations
 * @property size Size of the file in bytes, null if unknown
 */
data class FileSource(
    val name: String,
    val rawSource: RawSource,
    val mimeType: String,
    val size: Long? = null,
) {
    /**
     * Gets the file extension without the dot.
     * Returns null if no extension is found.
     */
    val extension: String?
        get() = name.substringAfterLast('.', "").takeIf { it.isNotEmpty() }

    /**
     * Checks if this file is a text file based on MIME type.
     */
    val isTextFile: Boolean
        get() = mimeType.startsWith("text/")

    /**
     * Checks if this file is a PDF.
     */
    val isPdf: Boolean
        get() = mimeType == "application/pdf" || extension?.lowercase() == "pdf"

    /**
     * Checks if this file is an EPUB.
     */
    val isEpub: Boolean
        get() = mimeType == "application/epub+zip" || extension?.lowercase() == "epub"

    /**
     * Checks if this file is a Markdown file.
     */
    val isMarkdown: Boolean
        get() = extension?.lowercase() in setOf("md", "markdown")
}
