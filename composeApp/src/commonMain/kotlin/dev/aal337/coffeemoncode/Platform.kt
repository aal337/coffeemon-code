package dev.aal337.coffeemoncode

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform