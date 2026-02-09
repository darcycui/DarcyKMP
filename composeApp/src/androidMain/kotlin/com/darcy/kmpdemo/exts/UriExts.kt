package com.darcy.kmpdemo.exts

import android.content.Context
import android.net.Uri
import com.darcy.kmpdemo.log.logD
import java.io.File

fun Uri.copyToCacheFile(context: Context, childFolder: String): File {
    val cacheDir = File(context.cacheDir, childFolder).apply { mkdirs() }
    val cacheFile = File(cacheDir, "${System.currentTimeMillis()}.jpg")
    return context.contentResolver.openInputStream(this)?.use { input ->
        cacheFile.outputStream().use { output ->
            input.copyTo(output)
            cacheFile.apply {
                logD("cacheFile path: $absolutePath")
            } // 返回缓存文件
        }
    } ?: File("")
}