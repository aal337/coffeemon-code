package io.github.aal337.coffeemoncode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import io.github.xxfast.kstore.KStore

import coffeemoncode.composeapp.generated.resources.Res
import coffeemoncode.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(store: KStore<GameData>) {
    MaterialTheme {
        val game = GameMemory(store)
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var seconds: Int? by remember { mutableStateOf(null) }
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            var inputFieldText by remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            TextField(
                value = inputFieldText,
                onValueChange = { inputFieldText = it },
                label = { Text("Hackatime username") }
            )
            Button(onClick = {
                coroutineScope.launch {
                    game.username = inputFieldText
                    seconds = getTime(inputFieldText)
                }
            }) {
                Text("Get time")
            }
            if (seconds != null) {
                Text(seconds.toString())
            }
            // var alreadyCaught by remember { mutableStateOf(0) }
            val catchable = (seconds ?: 0) / 1800 - game.coffeemonNumber ?: 0
            if (catchable < 0) {
                Text("You have too many Coffeemon, some will run away!")
                game.coffeemonNumber = (game.coffeemonNumber ?: 0) + catchable
            }
            Text("Hackatime time: ${seconds ?: 0} seconds")
            Text("That's $catchable Coffeemon catchable!")
            Button(onClick = { game.coffeemonNumber = (game.coffeemonNumber ?: 0) + 1 }) {
                Text("Catch one!")
            }
        }
    }
}