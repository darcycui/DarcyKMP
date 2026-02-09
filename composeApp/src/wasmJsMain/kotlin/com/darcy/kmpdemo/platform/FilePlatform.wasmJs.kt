package com.darcy.kmpdemo.platform

import kotlinx.io.files.Path

actual object FilePlatform {
    actual fun getCacheDir(): Path {
        TODO("Not yet implemented")
    }

    actual fun getDocumentsDir(): Path {
        TODO("Not yet implemented")
    }

    actual fun getDownloadDir(): Path {
        TODO("Not yet implemented")
    }

    actual suspend fun dealUriIfNeed(uriPath: Path): Path {
        TODO("Not yet implemented")
    }
}