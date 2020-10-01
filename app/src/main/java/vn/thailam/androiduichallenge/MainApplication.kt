package vn.thailam.androiduichallenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import vn.thailam.challenge1.core.di.Challenge1Modules.challenge1Modules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(challenge1Modules)
        }
    }
}
