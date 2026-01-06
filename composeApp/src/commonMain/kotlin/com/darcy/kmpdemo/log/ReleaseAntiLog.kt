package com.darcy.kmpdemo.log

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

class ReleaseAntiLog : Antilog() {
    override fun isEnable(priority: LogLevel, tag: String?): Boolean {
        return false
    }

    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        // do nothing
    }
}