package com.darcy.kmpdemo.platform


class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val version: String = "1.0.0"
}

actual fun getPlatform(): Platform {
    return WasmPlatform()
}