package io.github.aal337.coffeemoncode

import android.os.Build
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val httpEngine = OkHttp
}

actual fun getPlatform(): Platform = AndroidPlatform()