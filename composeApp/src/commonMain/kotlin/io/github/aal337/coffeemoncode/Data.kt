package io.github.aal337.coffeemoncode

import kotlinx.serialization.*
import io.ktor.client.*
import io.ktor.client.engine.HttpClientEngine

@Serializable
data class Response(val total_seconds: Int)

suspend fun getTime(user: String, engine: HttpClientEngine): Int {
    return Json { ignoreUnknownKeys = true }.decodeFromString<Response>(HttpClient(engine).get("https://api.hackatime.hackclub.com/api/v1/users/$user/stats").body<String>()).total_seconds
}