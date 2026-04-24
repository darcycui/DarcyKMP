package com.darcy.kmpdemo.platform

import com.darcy.kmpdemo.log.logV
import com.darcy.kmpdemo.ssl.SslSettings

actual fun configureEngineTLS(engineConfig: Any) {
    if (engineConfig is io.ktor.client.engine.cio.CIOEngineConfig) {
        engineConfig.https{
            trustManager = SslSettings.getTrustManager()
        }
    }
}


actual suspend fun sslCertsConfig(certsList: List<ByteArray>) {
    logV("sslCertsConfig: init ssl certs")
    SslSettings.initCertBytes(certsList)
}