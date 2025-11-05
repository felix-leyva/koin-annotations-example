package de.felixlf.koinannotationskmpsample.entity.features

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Single

/**
 * Provides the dispatchers for the application.
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val inmediate: CoroutineDispatcher
    val io: CoroutineDispatcher

    fun newUIScope(): UIModelScope
}

@Single
class DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val inmediate: CoroutineDispatcher = Dispatchers.Main.immediate
    override val io: CoroutineDispatcher = Dispatchers.Default

    override fun newUIScope(): UIModelScope {
        return CoroutineScope(inmediate + SupervisorJob())
    }
}

typealias UIModelScope = CoroutineScope
