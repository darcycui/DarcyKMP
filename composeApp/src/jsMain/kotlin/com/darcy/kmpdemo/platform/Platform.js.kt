package com.darcy.kmpdemo.platform

class JsPlatform : Platform {
    override val name: String = "Web with Kotlin/JS"
    override val version: String = "1.0.0"
}

actual fun getPlatform(): Platform {
    return JsPlatform()
}