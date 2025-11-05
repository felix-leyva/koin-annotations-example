package de.felixlf.koinannotationskmpsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.felixlf.koinannotationskmpsample.features.mainscreen.MainScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<MainScreenViewModel>()
        val text by viewModel.text.collectAsState()

        Column(
            modifier =
                Modifier.background(MaterialTheme.colorScheme.primaryContainer).safeContentPadding()
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                viewModel.loadText()
            }) {
                Text("Click me!")
            }
            text?.let {
                LazyColumn {
                    item {
                        Text(it)
                    }
                }
            }
        }
    }
}
