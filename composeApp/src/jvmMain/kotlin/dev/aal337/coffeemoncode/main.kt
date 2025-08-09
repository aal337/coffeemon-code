package dev.aal337.coffeemoncode

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CoffeemonCode",
    ) {
        App()
    }
}