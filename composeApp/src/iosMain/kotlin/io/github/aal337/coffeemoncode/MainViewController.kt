package io.github.aal337.coffeemoncode

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val fileManager: NSFileManager = NSFileManager.defaultManager
    val documentsUrl: NSURL = fileManager.URLForDirectory(
        directory = NSDocumentDirectory,
        appropriateForURL = null,
        create = false,
        inDomain = NSUserDomainMask,
        error = null
    )!!
    App(store = getKStore(dir = documentsUrl.path))
}