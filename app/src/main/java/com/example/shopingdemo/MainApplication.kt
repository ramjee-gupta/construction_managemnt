package com.example.shopingdemo

import android.app.Application
import com.example.shopingdemo.database.dbModule
import com.example.shopingdemo.koinmodules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(retrofitModule, dbModule)
        }
    }
}