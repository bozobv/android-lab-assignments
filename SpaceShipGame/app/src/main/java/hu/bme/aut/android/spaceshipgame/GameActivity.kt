package hu.bme.aut.android.spaceshipgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.android.spaceshipgame.sensor.GyroscopeHelper
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private lateinit var gyroscopeHelper: GyroscopeHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gyroscopeHelper = GyroscopeHelper(this, gameView)
    }

    override fun onResume() {
        super.onResume()
        gyroscopeHelper.start()
    }

    override fun onPause() {
        gyroscopeHelper.stop()
        super.onPause()
    }
}
