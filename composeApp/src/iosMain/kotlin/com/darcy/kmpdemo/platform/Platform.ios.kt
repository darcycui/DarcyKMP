package com.darcy.kmpdemo.platform

class IOSPlatform : Platform {
    override val name: String = "iOS with Kotlin"
    override val version: String = "1.0.0"
}

actual fun getPlatform(): Platform {
    return IOSPlatform()
}