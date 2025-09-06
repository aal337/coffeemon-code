package io.github.aal337.coffeemoncode

import io.ktor.client.engine.apache5.*

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val httpEngine = Apache5
}

actual fun getPlatform(): Platform = JVMPlatform()