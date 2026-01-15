package com.darcy.kmpdemo.utils

import com.darcy.kmpdemo.platform.KeyValueStorage
import com.russhwolf.settings.Settings


class KeyValueHelper private constructor(private val storageName: String) {
    private var keyValueStorage: Settings? = null

    companion object {
        private var instance: KeyValueHelper? = null
        private const val STORAGE_GLOBAL = "global"

        fun getInstance(storageName: String = STORAGE_GLOBAL): KeyValueHelper {
            return instance ?: run {
                instance = KeyValueHelper(storageName)
                instance!!
            }
        }
    }

    fun getSettings(): Settings {
        return keyValueStorage ?: run {
            keyValueStorage = KeyValueStorage.createKeyValueStorage(storageName)
            keyValueStorage!!
        }
    }

    fun putInt(key: String, value: Int) {
        getSettings().putInt(key, value)
    }

    fun putLong(key: String, value: Long) {
        getSettings().putLong(key, value)
    }

    fun putString(key: String, value: String) {
        getSettings().putString(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        getSettings().putBoolean(key, value)
    }

    fun putFloat(key: String, value: Float) {
        getSettings().putFloat(key, value)
    }

    fun putDouble(key: String, value: Double) {
        getSettings().putDouble(key, value)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return getSettings().getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return getSettings().getLong(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String): String {
        return getSettings().getString(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getSettings().getBoolean(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return getSettings().getFloat(key, defaultValue)
    }

    fun getDouble(key: String, defaultValue: Double): Double {
        return getSettings().getDouble(key, defaultValue)
    }

    fun remove(key: String) {
        getSettings().remove(key)
    }

    fun clear() {
        getSettings().clear()
    }

}