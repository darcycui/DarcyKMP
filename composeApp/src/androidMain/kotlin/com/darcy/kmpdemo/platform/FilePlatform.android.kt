package com.darcy.kmpdemo.platform

import android.content.Context
import androidx.core.content.ContextCompat
import com.darcy.kmpdemo.app.AppContextProvider
import kotlinx.io.files.Path
import java.io.File

actual object FilePlatform {

    // 获取 Android 上下文
    private fun getAndroidContext(): Context {
        return AppContextProvider.getAppContext()
    }

    actual fun getCacheDir(): Path {
        val context = getAndroidContext()
        val file = context.externalCacheDirs.firstOrNull()
            ?: context.externalCacheDir
        return Path(file?.absolutePath ?: "")
    }

    actual fun getDocumentsDir(): Path {
        val context = getAndroidContext()
        val file = context.getExternalFilesDirs(DarcyFolder.DIR_DOCUMENT).firstOrNull()
            ?: context.getExternalFilesDir(DarcyFolder.DIR_DOCUMENT)
        return Path(file?.absolutePath ?: "")

    }

    actual fun getDownloadDir(): Path {
        val context = getAndroidContext()
        val file = context.getExternalFilesDirs(DarcyFolder.DIR_DOWNLOAD).firstOrNull()
            ?: context.getExternalFilesDir(DarcyFolder.DIR_DOCUMENT)
        return Path(file?.absolutePath ?: "")
    }
}