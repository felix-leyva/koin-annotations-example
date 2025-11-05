package de.felixlf.koinannotationskmpsample.features.mainscreen

import androidx.lifecycle.ViewModel
import de.felixlf.koinannotationskmpsample.entity.features.mainscreen.MainScreenModel
import de.felixlf.koinannotationskmpsample.entity.features.mainscreen.MainScreenModelImpl
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainScreenViewModel(
    // Injects the implementation, to access the CoroutineScope and use it as delegate
    private val mainScreenModelImpl: MainScreenModelImpl,
) : ViewModel(viewModelScope = mainScreenModelImpl.modelScope), // Links the CoroutineScope with the viewModel lifecycle
    MainScreenModel by mainScreenModelImpl // Implement the interface delegating to the implementation
