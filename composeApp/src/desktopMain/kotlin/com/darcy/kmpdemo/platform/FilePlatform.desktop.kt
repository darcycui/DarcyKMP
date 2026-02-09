package com.darcy.kmpdemo.platform

import com.darcy.kmpdemo.utils.AppHelper
import kotlinx.io.files.Path
import java.io.File

actual object FilePlatform {
    actual fun getCacheDir(): Path {
        return Path(System.getProperty("user.home"), "Cache/${AppHelper.getAppName()}")
    }

    actual fun getDocumentsDir(): Path {
        return Path(System.getProperty("user.home"), "Documents/${AppHelper.getAppName()}")
    }

    actual fun getDownloadDir(): Path {
        return Path(System.getProperty("user.home"), "Downloads/${AppHelper.getAppName()}")
    }

    actual suspend fun dealUriIfNeed(uriPath: Path): Path {
        return uriPath
    }

}