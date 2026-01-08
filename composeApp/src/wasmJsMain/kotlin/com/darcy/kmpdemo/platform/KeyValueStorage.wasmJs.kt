package com.darcy.kmpdemo.platform

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

actual object KeyValueStorage {
    actual fun createKeyValueStorage(storageName: String): Settings {
        return StorageSettings() // 使用localStorage
    }
}