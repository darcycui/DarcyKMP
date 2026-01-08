package com.darcy.kmpdemo.platform

import androidx.compose.runtime.Composable
import kotlinx.io.files.Path

actual class ImagePicker {
    actual suspend fun pickImage(): Path {
        TODO("Not yet implemented")
    }
}

@Composable
actual fun ShowUploadImage() {
}