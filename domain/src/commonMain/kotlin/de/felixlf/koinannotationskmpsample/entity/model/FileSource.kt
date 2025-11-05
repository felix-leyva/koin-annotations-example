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
)
