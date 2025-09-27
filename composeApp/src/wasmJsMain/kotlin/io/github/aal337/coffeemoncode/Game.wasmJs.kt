package io.github.aal337.coffeemoncode

import io.github.xxfast.kstore.storage.storeOf

actual fun getKStore(dir: String) = storeOf(key = "game_data")