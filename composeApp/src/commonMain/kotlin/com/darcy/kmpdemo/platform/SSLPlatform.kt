package com.darcy.kmpdemo.platform

// SSL证书初始化
expect suspend fun sslCertsConfig(certsList: List<ByteArray>)

// 给Ktor配置SSL证书 不同平台提供不同实现
expect fun configureEngineTLS(engineConfig: Any): Unit