package hu.bme.aut.android.adbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import hu.bme.aut.android.adbrowser.databinding.ActivityMainBinding
import hu.bme.aut.android.adbrowser.notification.AlarmHelper
import hu.bme.aut.android.adbrowser.notification.NotificationHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToDetails(intent)
    }

    private fun navigateToDetails(intent: Intent) {
        if (intent.action == NotificationHelper.ACTION_SHOW_AD) {
            val adId = intent.getLongExtra(NotificationHelper.AD_ID, 0)
            val action = AdbrowserNavGraphDirections.actionShowDetails(adId)
            findNavController(R.id.nav_host_fragment).navigate(action)
        }
    }

    override fun onPause() {
        super.onPause()
        val pendingIntent = NotificationHelper.createPendingIntentForPromotionNotification(this)
        AlarmHelper.scheduleAlarm(this, 10, pendingIntent)
    }

}
