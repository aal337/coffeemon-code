package io.github.aal337.coffeemoncode

import io.github.xxfast.kstore.KStore
import kotlinx.serialization.Serializable

@Serializable
data class GameData(val username: String, val seconds: Int, val coffeemonNumber: Int)

expect fun getKStore(dir: String): KStore<GameData>

class GameMemory(val store: KStore<GameData>) {
    suspend fun setUsername(value: String?) {
            store.update { it?.copy(username = value ?: "") }
    }
    suspend fun getCoffeemonNumber() = store.get()?.coffeemonNumber
    suspend fun setCoffeemonNumber(value: Int?) {
        store.update { it?.copy(coffeemonNumber = value ?: 0) }
    }
}