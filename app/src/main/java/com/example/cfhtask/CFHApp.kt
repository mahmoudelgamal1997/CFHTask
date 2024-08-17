package com.example.cfhtask

import android.app.Application
import com.example.cfhtask.di.databaseBuilder
import com.example.cfhtask.di.movieUseCaseModule
import com.example.cfhtask.di.repositoryModule
import com.example.cfhtask.di.viewModelModule
import com.example.netoworklib.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CFHApp() : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CFHApp)
            modules(listOf(networkModule,repositoryModule,viewModelModule,movieUseCaseModule,databaseBuilder))
        }
    }
}
