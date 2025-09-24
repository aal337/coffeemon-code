package io.github.aal337.coffeemoncode

import io.ktor.client.engine.*

interface Platform {
    val name: String
    val httpEngine: HttpClientEngineFactory<HttpClientEngineConfig>
}

expect fun getPlatform(): Platform