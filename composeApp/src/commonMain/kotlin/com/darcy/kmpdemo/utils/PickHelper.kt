package com.darcy.kmpdemo.utils

import com.darcy.kmpdemo.platform.ImagePicker
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.openFilePicker
import io.github.vinceglb.filekit.utils.toPath
import kotlinx.io.files.Path

object PickHelper {
    suspend fun pickImage(): Path {
        val file = FileKit.openFilePicker(type = FileKitType.Image) ?: return Path("")
        return file.toString().toPath()
    }

    suspend fun pickFile(): Path {
        val file = FileKit.openFilePicker() ?: return Path("")
        return file.toString().toPath()
    }

    suspend fun pickMultiFile(fileCount: Int? = null): List<Path> {
        val files =
            FileKit.openFilePicker(mode = FileKitMode.Multiple(fileCount)) ?: return emptyList()
        return files.map { it.toString().toPath() }
    }
}