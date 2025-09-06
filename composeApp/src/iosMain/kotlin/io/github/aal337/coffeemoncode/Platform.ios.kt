package io.github.aal337.coffeemoncode

import platform.UIKit.UIDevice
import io.ktor.client.engine.darwin.*

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val httpEngine = Darwin
}

actual fun getPlatform(): Platform = IOSPlatform()