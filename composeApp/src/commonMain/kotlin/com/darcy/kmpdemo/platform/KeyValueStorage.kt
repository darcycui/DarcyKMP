package com.darcy.kmpdemo.platform

import com.russhwolf.settings.Settings

expect object KeyValueStorage {
    fun createKeyValueStorage(storageName: String): Settings
}