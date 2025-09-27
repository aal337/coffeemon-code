package io.github.aal337.coffeemoncode

actual fun getKStore(dir: String) = storeOf(file = Path("$dir/game_data.json"))