package com.darcy.kmpdemo.platform

import com.darcy.kmpdemo.app.AppContextProvider
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual object KeyValueStorage {
    actual fun createKeyValueStorage(storageName: String): Settings {
        return SharedPreferencesSettings.Factory(AppContextProvider.getAppContext()).create()
    }
}