package com.darcy.kmpdemo.platform

import kotlinx.io.files.Path
import java.io.File

actual fun createDirectory(path: String): Boolean {
    return File(path).mkdirs()
}

actual fun getCacheDir(): Path {
    return Path(System.getProperty("user.home"), ".cache/your_app_name")
}

actual fun getDocumentsDir(): Path {
    return Path(System.getProperty("user.home"), "Documents")
}

actual fun getDownloadDir(): Path {
    return Path(System.getProperty("user.home"), "Downloads")
}

actual fun createFile(path: String): Boolean {
    TODO("Not yet implemented")
}