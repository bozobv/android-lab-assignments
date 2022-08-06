package hu.bme.aut.android.adbrowser

import android.app.Application
import hu.bme.aut.android.adbrowser.notification.NotificationHelper
import hu.bme.aut.android.adbrowser.repository.AdRepository

class AdBrowserApplication : Application() {

    companion object {
        lateinit var adRepository: AdRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        adRepository = AdRepository()
        NotificationHelper.createNotificationChannels(this)
    }

}
