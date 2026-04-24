package com.darcy.kmpdemo.network.http.impl.ktor

import com.darcy.kmpdemo.log.logD
import io.ktor.client.plugins.logging.Logger


class KtorLogger: Logger {
    override fun log(message: String) {
        // 添加文件下载专用日志
        if (message.contains("Content-Type: image") ||
            message.contains("Content-Type: application/octet-stream")
        ) {
            logD(
                tag = KTOR_FILE_TAG,
                msg = "-------------------------------------\n${
                    message.substringBefore("BODY START")
                }\n-------------------------------------"
            )
        } else {
            logD(
                tag = KTOR_TAG,
                msg = "-------------------------------------\n$message\n-------------------------------------"
            )
        }
    }

}