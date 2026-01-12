package com.darcy.kmpdemo.platform

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.darcy.kmpdemo.log.logD
import com.darcy.kmpdemo.log.logE
import kotlinx.coroutines.CoroutineScope
import kotlinx.io.files.Path
import java.awt.EventQueue
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

actual object ImagePicker {
//    actual suspend fun pickImage(): Path {
//        val path = chooseFileWithNativeDialog() ?: ""
//        if (path.isEmpty()) {
//            logE("文件选择错误")
//            return Path("")
//        }
//        val sourceFile = File(path)
//        val documentDir = File(getDocumentsDir().toString() + "/image")
////        if (!documentDir.exists()) {
////            createDirectory(documentDir.path)
////        }
//        val fileName = path.substringAfterLast(".")
//        val cacheFile = File(documentDir, "${System.currentTimeMillis()}_.$fileName")
//        sourceFile.copyTo(cacheFile, true)
//        logD("选择文件成功：${cacheFile.absolutePath}")
//        return Path(cacheFile.absolutePath)
//    }


//    actual suspend fun pickImage(): File {
//        // 选择文件
//        val dialog = FileDialog(null as? Frame, "选择文件").apply {
//            mode = FileDialog.LOAD // 读文件
//            isVisible = true // 可见
//            isMultipleMode = false // 单选
//            filenameFilter = object : FilenameFilter {
//                override fun accept(dir: File, name: String): Boolean {
//                    logD("name: $name")
//                    return name.lowercase().endsWith(".png")
//                }
//            }
//        }
//        return dialog.file?.let { fileName ->
//            val sourceFile = File(dialog.directory, fileName)
//            val documentDir = getDocumentsDir().resolve("image")
//            if (!documentDir.exists()) {
//                createADirectory(documentDir.path)
//            }
//            val cacheFile = File(documentDir, "${System.currentTimeMillis()}_$fileName")
//            sourceFile.copyTo(cacheFile, true)
//            logD("选择文件成功：${cacheFile.absolutePath}")
//            cacheFile
//        } ?: run {
//            logE("选择文件失败")
//            File("")
//        }
//    }

    fun chooseFileWithNativeDialog(): String? {
        var selectedFilePath: String? = null

        // 确保文件选择器的创建和显示在事件分发线程（EDT）上执行
        EventQueue.invokeAndWait {
            val fileChooser = JFileChooser().apply {
                // 可选：设置文件过滤器，例如只显示文本文件
                fileFilter = FileNameExtensionFilter("*.png", "png")
                // 可选：设置对话框标题
                dialogTitle = "选择一个文件"
            }

            val result = fileChooser.showOpenDialog(null) // 显示打开文件对话框

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFilePath = fileChooser.selectedFile.absolutePath
            }
        }
        return selectedFilePath
    }

    actual suspend fun pickImage(): Path {
        TODO("Not yet implemented")
    }
}

@Composable
actual fun ShowUploadImage() {
    val scope: CoroutineScope = rememberCoroutineScope()
    val filePath: MutableState<String> = remember { mutableStateOf("unknown") }
    val imageBitmap: MutableState<ImageBitmap?> = remember { mutableStateOf(null) }
    val scrollState: ScrollState = rememberScrollState()
    val imagePicker: ImagePicker = remember { ImagePicker }
    val uploadResult: MutableState<String> = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.verticalScroll(scrollState).fillMaxSize(),
        // 垂直间距
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(onClick = {
//            uploadFile(scope, imagePicker, filePath, imageBitmap, uploadResult)
        }) {
            Text(text = "选择并上传图片")
        }
        Text(text = filePath.value)
        Text(text = uploadResult.value)
        imageBitmap.value?.let {
            Image(
                bitmap = it,
                contentDescription = "本地图片",
                modifier = Modifier.fillMaxSize()
            )
        } ?: Text("无法加载图片")

    }
}