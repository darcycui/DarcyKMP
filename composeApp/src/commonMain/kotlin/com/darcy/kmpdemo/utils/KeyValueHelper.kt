package com.darcy.kmpdemo.utils

import com.darcy.kmpdemo.platform.KeyValueStorage
import com.russhwolf.settings.Settings

object KeyValueHelper {
    private val keyValueStorage: Settings? = null
    private const val STORAGE_GLOBAL = "global"

    fun getInstance(storageName: String = STORAGE_GLOBAL): Settings {
        return keyValueStorage ?: run {
            val keyValueStorage = KeyValueStorage.createKeyValueStorage(storageName)
            keyValueStorage
        }
    }
}