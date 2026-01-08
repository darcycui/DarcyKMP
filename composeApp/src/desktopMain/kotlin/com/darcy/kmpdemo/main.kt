package com.darcy.kmpdemo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.kotlin.fibonacci.generateFibi

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "解密工具",
    ) {
        App()
        val x = generateFibi().take(3).last()
        println("fibonacci: $x")
    }
}