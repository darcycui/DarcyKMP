package com.darcy.kmpdemo.utils

object EncryptHelper {
    fun encryptString(str: String?): String {
        return str?.reversed() ?: ""
    }

    fun decryptString(str: String?): String {
        return str?.reversed() ?: ""
    }
}