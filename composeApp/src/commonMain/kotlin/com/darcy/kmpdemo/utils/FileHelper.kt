package com.darcy.kmpdemo.utils

import com.darcy.kmpdemo.log.logE
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.isSuccess
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readAvailable
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem

object FileHelper {
    fun createDirectories(path: Path): Boolean {
        return runCatching {
            SystemFileSystem.createDirectories(path)
            true
        }.onFailure {
            logE("createDirectory error: ${it.message}")
            it.printStackTrace()
        }.getOrElse {
            false
        }
    }

    fun exists(path: Path): Boolean {
        return SystemFileSystem.exists(path)
    }

    fun createFile(path: Path): Boolean {
        return runCatching {
            if (exists(path).not()) {
                SystemFileSystem.sink(path).use {
                }
            }
            true
        }.onFailure {
            logE("createFile error: ${it.message}")
            it.printStackTrace()
        }.getOrElse {
            false
        }
    }

    suspend fun readFileBuffered(path: Path, action: (ByteArray) -> Unit) {
        runCatching {
            SystemFileSystem.source(path).buffered().use { source ->
                // 从 source 中读取字节数据
                val buffer = ByteArray(8096)  // 缓冲区大小
                var bytesRead: Int

                while (true) {
                    bytesRead = source.readAtMostTo(buffer, 0, buffer.size)
                    if (bytesRead == -1) break  // 文件结束

                    // 处理读取到的字节数组
                    val actualBytes = buffer.sliceArray(0 until bytesRead)
                    // 在这里处理 actualBytes 数据
                    action(actualBytes)
                }
            }
        }.onFailure {
            logE("readFileBuffered error: ${it.message}")
            it.printStackTrace()
        }
    }

    suspend fun writeFileByArrayBuffered(
        path: Path,
        byteArray: ByteArray,
    ) {
        SystemFileSystem.sink(path).buffered().use { sink ->
            sink.write(byteArray)
            sink.flush()
        }
    }

    suspend fun writeFileByChannelBuffered(
        path: Path,
        channel: ByteReadChannel
    ) {
        SystemFileSystem.sink(path).buffered().use { sink ->
            // 从响应体读取数据并写入文件
            val buffer = ByteArray(8192)
            var bytesRead: Int
            while (true) {
                // 直接调用 readAvailable，因为外层已经是 suspend 函数
                bytesRead = channel.readAvailable(buffer, 0, buffer.size)
                if (bytesRead <= 0) break
                sink.write(buffer, 0, bytesRead)
            }
            sink.flush()
        }
    }

    suspend fun callReadFile(path: Path): Unit {
        readFileBuffered(path) { bufferedData ->
            val line = bufferedData.decodeToString()
            logE("Read file line: $line")
        }
    }

    suspend fun callWriteFile(path: Path): Unit {
        val content = "Hello, Stream Writing!"
        writeFileByArrayBuffered(path, content.encodeToByteArray())
    }

    suspend fun downloadAndSaveFile(
        client: HttpClient,
        url: String,
        savePath: Path
    ): Boolean {
        return try {
            val response = client.get(url)

            if (response.status.isSuccess()) {
                // 获取响应体的字节流
                val channel = response.bodyAsChannel()
//                    val source: RawSource = channel.asSource()
                // 使用 writeFileBuffered 写入文件
                writeFileByChannelBuffered(savePath, channel)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            logE("Download failed: ${e.message}")
            e.printStackTrace()
            false
        }
    }
}