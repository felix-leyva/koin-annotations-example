package de.felixlf.koinannotationskmpsample.entity.repository

import org.koin.core.annotation.Single

interface Logger {
    fun log(message: String)
}

/**
 * Dummy implementation of a logger
 */
@Single
internal class LoggerImpl : Logger {
    override fun log(message: String) {
        println("message")
    }
}
