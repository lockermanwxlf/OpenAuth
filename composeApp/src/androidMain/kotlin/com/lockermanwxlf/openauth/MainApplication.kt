package com.lockermanwxlf.openauth

import android.app.Application
import com.lockermanwxlf.openauth.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}