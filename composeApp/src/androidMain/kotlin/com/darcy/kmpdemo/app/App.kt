package com.darcy.kmpdemo.app

import android.app.Application
import android.content.Context
import com.darcy.kmpdemo.log.initLogger

class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        AppContextProvider.init(this)
        initLogger()
    }
}