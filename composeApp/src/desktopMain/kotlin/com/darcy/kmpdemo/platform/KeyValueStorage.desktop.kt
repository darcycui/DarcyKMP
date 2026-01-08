package com.darcy.kmpdemo.platform

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings


actual object KeyValueStorage {
    actual fun createKeyValueStorage(storageName: String): Settings {
        return PreferencesSettings.Factory().create(storageName)
    }
}