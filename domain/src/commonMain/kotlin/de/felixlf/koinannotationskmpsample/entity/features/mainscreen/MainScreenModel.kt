package de.felixlf.koinannotationskmpsample.entity.features.mainscreen

import arrow.core.raise.either
import de.felixlf.koinannotationskmpsample.entity.features.DispatcherProvider
import de.felixlf.koinannotationskmpsample.entity.features.UIModelScope
import de.felixlf.koinannotationskmpsample.entity.repository.FilePicker
import de.felixlf.koinannotationskmpsample.entity.repository.FileReader
import de.felixlf.koinannotationskmpsample.entity.repository.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

/**
 * ScreenModel of the MainScreen
 */
interface MainScreenModel {
    val text: StateFlow<String?>

    fun loadText()
}

/**
 * The implementation of the ScreenModel of the MainScreen
 * Notice, that this class does not depends on the Jetpack ViewModel, so it could be used directly by any UI framework if
 * needed. The only important thing, is to manage the modelScope lifecycle correctly
 */
@Factory
class MainScreenModelImpl(
    dispatcherProvider: DispatcherProvider,
    // The dependencies below, are implemented in another module, which is not accessible to this onw. To avoid the KSP
    //  check fails, we add the annotation @Provided, to indicate this type is provided externally
    @Provided private val filePicker: FilePicker,
    @Provided private val fileReader: FileReader,
    private val logger: Logger,
) : MainScreenModel {
    val modelScope: UIModelScope = dispatcherProvider.newUIScope()

    private val mText = MutableStateFlow<String?>(null)
    override val text = mText.asStateFlow()

    override fun loadText() {
        modelScope.launch {
            mText.value =
                either {
                    val file = filePicker.pickFile().bind()
                    fileReader.readTextFile(file).bind()
                }.onLeft {
                    logger.log(it.message)
                }.getOrNull()
        }
    }
}
