package io.github.daeun1012.withhotels

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            //debug 모드 일때만 초기화
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}