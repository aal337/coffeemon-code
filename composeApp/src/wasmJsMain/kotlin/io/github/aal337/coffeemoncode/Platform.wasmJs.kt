package io.github.aal337.coffeemoncode

import io.ktor.client.engine.js.*

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val httpEngine = Js
}

actual fun getPlatform(): Platform = WasmPlatform()