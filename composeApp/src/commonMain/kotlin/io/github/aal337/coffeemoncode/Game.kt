package io.github.aal337.coffeemoncode

import kotlinx.serialization.Serializable
import kotlin.io.*
import io.github.xxfast.kstore.KStore

@Serializable
data class GameData(val username: String, val seconds: Int, val coffeemonNumber: Int)

expect fun getKStore(dir: String): KStore<GameData>

class GameMemory(val store: KStore<GameData>) {
    var username: String?
        get() = store.get()?.username
        set(value) {
            store.update { it?.copy(username = value) }
            field = value
        }
    var coffeemonNumber: Int?
        get() = store.get()?.coffeemonNumber
        set(value) {
            store.update { it?.copy(coffeemonNumber = value) }
            field = value
        }
}