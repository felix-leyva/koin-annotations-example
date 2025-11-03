package de.felixlf.koinannotationskmpsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import arrow.core.raise.either
import de.felixlf.koinannotationskmpsample.entity.repository.FilePicker
import de.felixlf.koinannotationskmpsample.entity.repository.FileReader
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        var text by remember { mutableStateOf<String?>(null) }
        val filePicker = koinInject<FilePicker>()
        val fileReader = koinInject<FileReader>()
        val scope = rememberCoroutineScope()

        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                scope.launch {
                    text =
                        either {
                            val file = filePicker.pickFile().bind()
                            fileReader.readTextFile(file).bind()
                        }.onLeft {
                            println(it)
                        }.getOrNull()
                }
            }) {
                Text("Click me!")
            }
            text?.let {
                Text(it)
            }
        }
    }
}
