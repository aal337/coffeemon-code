package io.github.aal337.coffeemoncode

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Response(@Suppress("PropertyName") val total_seconds: Int)

private val json = Json {
    ignoreUnknownKeys = true
}

suspend fun getTime(username: String): Int {
    return json.decodeFromString<Response>(
        HttpClient(getPlatform().httpEngine).get(
            "https://hackatime.hackclub.com/api/v1/users/$username/stats"
        ).body<String>()
    ).total_seconds
}