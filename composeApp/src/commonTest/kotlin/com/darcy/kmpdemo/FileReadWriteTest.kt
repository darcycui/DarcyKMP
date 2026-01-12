package com.darcy.kmpdemo

import com.darcy.kmpdemo.platform.FilePlatform
import com.darcy.kmpdemo.utils.FileHelper
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.io.files.Path
import kotlin.test.Test

class FileReadWriteTest {
    @Test
    fun `test-write-text-to-file-and-read-it`() {
        val message = "hello world. 你好世界2"
        val folderPath = FilePlatform.getDocumentsDir()
        FileHelper.createDirectories(folderPath)
        val filePath: Path = Path(folderPath, "message.txt")
        FileHelper.createFile(filePath)
        runTest {
//            FileHelper.writeFileByArrayBuffered(
//                filePath,
//                message.encodeToByteArray()
//            )
            FileHelper.writeFileByChannelBuffered(filePath, ByteReadChannel(message))

            FileHelper.readFileBuffered(filePath, action = {
                val decodeToString = it.decodeToString()
                println("read file content: $decodeToString")
            })
        }
    }
}