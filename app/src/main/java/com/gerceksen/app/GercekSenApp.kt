package com.gerceksen.app

import android.app.Application

class GercekSenApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.init(this)
    }
}
