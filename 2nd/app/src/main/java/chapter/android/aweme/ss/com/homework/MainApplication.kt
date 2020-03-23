package chapter.android.aweme.ss.com.homework

import android.app.Application
import java.util.*

class MainApplication : Application() {
    var globalVar = HashMap<String, Any>()
    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}