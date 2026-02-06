package com.darcy.kmpdemo.utils

object HexHelper {
    fun bytesToHex(bytes: ByteArray): String {
        val hexArray = "0123456789ABCDEF".toCharArray()
        val hexChars = CharArray(bytes.size * 2)
        for (i in bytes.indices) {
            val v = bytes[i].toInt() and 0xFF
            hexChars[i * 2] = hexArray[v ushr 4]
            hexChars[i * 2 + 1] = hexArray[v and 0x0F]
        }
        return hexChars.joinToString("")
    }

    fun hexToBytes(hex: String): ByteArray {
        val hexChars = hex.toCharArray()
        val bytes = ByteArray(hexChars.size / 2)
        for (i in bytes.indices) {
            val firstHex = hexChars[i * 2].toString()
            val secondHex = hexChars[i * 2 + 1].toString()
            bytes[i] = ((firstHex.toInt(16) shl 4) or secondHex.toInt(16)).toByte()
        }
        return bytes
    }
}