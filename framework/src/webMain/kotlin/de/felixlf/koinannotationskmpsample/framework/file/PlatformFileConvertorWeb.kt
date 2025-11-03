package de.felixlf.koinannotationskmpsample.framework.file

import de.felixlf.koinannotationskmpsample.entity.model.FileSource
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.exceptions.FileKitException
import io.github.vinceglb.filekit.mimeType
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.size
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.io.Buffer
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.koin.core.annotation.Single
import org.w3c.files.FileReader
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Single
class PlatformFileConvertorWeb(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) :
    PlatformFileConvertor {
    @OptIn(ExperimentalWasmJsInterop::class)
    override suspend fun convertPlatformFileToFileSource(platformFile: PlatformFile): FileSource {
        val rawSource = platformFile.readToBuffer()

        val file =
            with(platformFile) {
                FileSource(
                    name = name,
                    rawSource = rawSource,
                    mimeType = mimeType()?.primaryType ?: "",
                    size = size(),
                )
            }
        return file
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    internal suspend fun PlatformFile.readToBuffer(): Buffer =
        withContext(dispatcher) {
            suspendCancellableCoroutine { continuation ->
                val reader = FileReader()
                continuation.invokeOnCancellation { reader.abort() }

                reader.onload = { event ->
                    try {
                        val arrayBuffer =
                            event.target?.unsafeCast<FileReader>()?.result?.unsafeCast<ArrayBuffer>()
                                ?: throw FileKitException("Could not read file")

                        val bytes = Uint8Array(arrayBuffer)
                        val buffer = Buffer()
                        buffer.use {
                            for (i in 0 until bytes.length) {
                                ensureActive()
                                it.writeByte(bytes[i])
                            }
                        }

                        continuation.resume(buffer)
                    } catch (e: Exception) {
                        if (!continuation.isCancelled && e !is CancellationException) {
                            continuation.resumeWithException(e)
                        }
                    }
                }

                reader.onerror = {
                    if (!continuation.isCancelled) {
                        continuation.resumeWithException(FileKitException("Error reading file: ${reader.error}"))
                    }
                }

                reader.onabort = {
                    if (!continuation.isCancelled) continuation.cancel()
                }

                reader.readAsArrayBuffer(file)
            }
        }
}
