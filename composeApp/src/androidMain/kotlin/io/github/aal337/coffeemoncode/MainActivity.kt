package io.github.aal337.coffeemoncode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlin.io.path.Path

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val files = filesDir
        setContent {
            App(store = getKStore(dir = files))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}