package io.github.aal337.coffeemoncode

import kotlin.io.path.Path
import io.github.xxfast.kstore.storeOf

actual fun getKStore(dir: String) = storeOf<GameData>(file = Path("$dir/game_data.json"))