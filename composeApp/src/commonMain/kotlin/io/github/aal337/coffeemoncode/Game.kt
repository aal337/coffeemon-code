package io.github.aal337.coffeemoncode

import kotlinx.serialization.Serializable
import kotlin.io.*
import io.github.xxfast.kstore.KStore

@Serializable
data class GameData(val username: String, val coffeemonNumber: Int)

expect fun getKStore(dir: String): KStore<GameData>