package io.github.aal337.coffeemoncode

import kotlinx.io.files.Path
import io.github.xxfast.kstore.file.storeOf

actual fun getKStore(dir: String) = storeOf<GameData>(file = Path("$dir/game_data.json"))