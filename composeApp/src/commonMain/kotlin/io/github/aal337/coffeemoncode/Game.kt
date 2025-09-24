package io.github.aal337.coffeemoncode

import kotlinx.serialization.Serializable
import io.github.xxfast.kstore.KStore

@Serializable
data class GameData(val username: String, val coffeemonNumber: Int)

expect fun getKStore(): KStore<GameData>