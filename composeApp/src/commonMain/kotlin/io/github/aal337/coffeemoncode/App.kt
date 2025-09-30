package io.github.aal337.coffeemoncode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.launch

@Composable
fun App(store: KStore<GameData>) {
    MaterialTheme {
        val game = GameMemory(store)
        val coroutineScope = rememberCoroutineScope()
        var alreadyPlayed by remember { mutableStateOf(run {
            var game: GameData? = null
            coroutineScope.launch { game = store.get() }
            game != null
        }) }
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var seconds: Int? by remember { mutableStateOf(null) }
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }

            var inputFieldText by remember { mutableStateOf("") }

            TextField(
                value = inputFieldText,
                onValueChange = { inputFieldText = it },
                label = { Text("Hackatime username") }
            )
            Button(onClick = {
                coroutineScope.launch {
                    game.setUsername(inputFieldText)
                    seconds = getTime(inputFieldText)
                }
            }) {
                Text("Get time")
            }
            var catchable: Int by remember { mutableStateOf(0) }
            if (catchable == 0) {
                coroutineScope.launch {
                    catchable = ((seconds ?: 0) / 1800) - (game.getCoffeemonNumber() ?: 0)
                }
            }
            if (catchable < 0) {
                Text("You have too many Coffeemon, some will run away!")
                coroutineScope.launch { game.setCoffeemonNumber((game.getCoffeemonNumber() ?: 0) + catchable) }
            }
            Text("Hackatime time: ${seconds ?: 0} seconds")
            var coffeemonNumber by remember { mutableStateOf(0) }
            // coroutineScope.launch { coffeemonNumber = game.getCoffeemonNumber() ?: 0 }
            Text("That's $catchable Coffeemon catchable!")
            Button(onClick = {
                if (catchable > 0) {
                    coroutineScope.launch { game.setCoffeemonNumber((game.getCoffeemonNumber() ?: 0) + 1) }
                    catchable--
                    coffeemonNumber++
                }
            }) {
                Text("Catch one!")
            }
            Text("You have caught $coffeemonNumber Coffeemon")
        }
    }
}