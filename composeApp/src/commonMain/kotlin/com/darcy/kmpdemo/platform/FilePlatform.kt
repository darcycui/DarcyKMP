package com.darcy.kmpdemo.platform

import kotlinx.io.files.Path

object DarcyFolder {
    const val DIR_CACHE = "Cache"
    const val DIR_DOCUMENT = "Documents"
    const val DIR_DOWNLOAD = "Download"
}

expect object FilePlatform {

    // 期望函数：获取缓存目录
    fun getCacheDir(): Path

    // 期望函数：获取文档目录
    fun getDocumentsDir(): Path

    // 期望函数：获取下载目录
    fun getDownloadDir(): Path

    // 期望函数：处理uri android平台需要处理
    suspend fun dealUriIfNeed(uriPath: Path): Path

}
