package io.github.aal337.coffeemoncode

import io.github.xxfast.kstore.file.storeOf
import kotlinx.io.files.Path

actual fun getKStore(dir: String) = storeOf<GameData>(file = Path("$dir/game_data.json"))