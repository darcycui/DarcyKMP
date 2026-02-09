package com.darcy.kmpdemo.platform

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.darcy.kmpdemo.app.AppContextProvider
import com.darcy.kmpdemo.exts.copyToCacheFile
import com.darcy.kmpdemo.log.logE
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

    actual suspend fun dealUriIfNeed(uriPath: Path): Path {
        if (uriPath.toString().startsWith("content:/")) {
            logE("Android content:/ 需要单独缓存到私有目录 uriPath=$uriPath")
            val cacheFile = uriPath.toString().replace("content:/", "content://").toUri()
                .copyToCacheFile(getAndroidContext(), "image")
            return Path(cacheFile.absolutePath)
        } else {
            logE("Android 非content:/ 不需要处理 uriPath=$uriPath")
            return uriPath
        }
    }
}