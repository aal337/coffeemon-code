package io.github.aal337.coffeemoncode

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.engine.*

@Serializable
data class Response(val total_seconds: Int)

suspend fun getTime(user: String): Int {
    return Json {
        ignoreUnknownKeys = true
    }.decodeFromString<Response>(
        HttpClient(getPlatform().httpEngine).get(
            "https://api.hackatime.hackclub.com/api/v1/users/$user/stats"
        ).body<String>()
    ).total_seconds
}