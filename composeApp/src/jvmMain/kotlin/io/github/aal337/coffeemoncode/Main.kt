package io.github.aal337.coffeemoncode

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import java.nio.file.FileSystem
import net.harawata.appdirs.AppDirs
import net.harawata.appdirs.AppDirsFactory

const val PACKAGE_NAME = "io.github.xxfast.kstore"
const val VERSION = "1.0"
const val ORGANISATION = "xxfast"

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CoffeemonCode",
    ) {
        val filesDir = AppDirsFactory.getInstance().getUserDataDir(PACKAGE_NAME, VERSION, ORGANISATION)
        with(SystemFileSystem) { if(!exists(Path(filesDir))) createDirectories(Path(filesDir)) }
        App(store = getKStore(dir = filesDir))
    }
}