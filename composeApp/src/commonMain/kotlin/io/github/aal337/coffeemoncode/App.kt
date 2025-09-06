package io.github.aal337.coffeemoncode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import coffeemoncode.composeapp.generated.resources.Res
import coffeemoncode.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var seconds: Int? by remember { mutableStateOf(null) }
            CatchingInterface(seconds)
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
                GetTimeInterface()
            }
        }
    }
}

@Composable
@Preview
fun GetTimeInterface() {
    var inputFieldText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    TextField(
        value = inputFieldText,
        onValueChange = { inputFieldText = it },
        label = Text("Hackatime username")
    )
    Button(onClick = {
        coroutineScope.launch {
            seconds = getTimeInSeconds(inputFieldText)
        }
    }) {
        Text("Get time")
    }
    if (seconds != null) {
        Text(seconds.toString())
    }
}

@Composable
fun CatchingInterface(seconds: Int?) {
    var alreadyCaught by remember { mutableStateOf(0) }
    val catchable = (seconds ?: 0) / 1800 - alreadyCaught
    if (catchable < 0) {
        Text("You have too many Coffeemon, some will run away!")
        alreadyCaught += catchable
    }
    Text("Hackatime time: ${seconds ?: 0} seconds")
    Text("That's $catchable Coffeemon catchable!")
    Button(onClick = { alreadyCaught++ }) {
        Text("Catch one!")
    }
}