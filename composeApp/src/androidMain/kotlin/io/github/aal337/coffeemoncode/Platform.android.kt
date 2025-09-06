package io.github.aal337.coffeemoncode

import android.os.Build
import io.ktor.client.engine.android.*

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val httpEngine = Android
}

actual fun getPlatform(): Platform = AndroidPlatform()