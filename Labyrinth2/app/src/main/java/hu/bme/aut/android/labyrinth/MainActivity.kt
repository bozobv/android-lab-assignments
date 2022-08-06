package hu.bme.aut.android.labyrinth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.labyrinth.databinding.ActivityMainBinding
import hu.bme.aut.android.labyrinth.events.MoveUserResponseEvent
import hu.bme.aut.android.labyrinth.events.WriteMessageResponseEvent
import hu.bme.aut.android.labyrinth.network.LabyrinthAPI
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MOVE_LEFT = 1
        private const val MOVE_UP = 2
        private const val MOVE_RIGHT = 3
        private const val MOVE_DOWN = 4
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var labyrinthAPI: LabyrinthAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        labyrinthAPI = LabyrinthAPI()

        binding.btnDown.setOnClickListener {
            async {
                val response = labyrinthAPI.moveUser(binding.etUsername.text.toString(), MOVE_DOWN)
                EventBus.getDefault().post(MoveUserResponseEvent(response))
            }
        }

        binding.btnUp.setOnClickListener {
            async {
                val response = labyrinthAPI.moveUser(binding.etUsername.text.toString(), MOVE_UP)
                EventBus.getDefault().post(MoveUserResponseEvent(response))
            }
        }

        binding.btnLeft.setOnClickListener {
            async {
                val response = labyrinthAPI.moveUser(binding.etUsername.text.toString(), MOVE_LEFT)
                EventBus.getDefault().post(MoveUserResponseEvent(response))
            }
        }

        binding.btnRight.setOnClickListener {
            async {
                val response = labyrinthAPI.moveUser(binding.etUsername.text.toString(), MOVE_RIGHT)
                EventBus.getDefault().post(MoveUserResponseEvent(response))
            }
        }
        binding.btnSend.setOnClickListener {
            async {
                val response = labyrinthAPI.writeMessage(binding.etUsername.text.toString(), binding.etMessage.text.toString())
                EventBus.getDefault().post(WriteMessageResponseEvent(response))
            }
        }


    }

    private fun async(call: () -> Unit) = Thread { call() }.start()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMoveUserResponse(event: MoveUserResponseEvent) {
        showResponse("Move User Response: ${event.response}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onWriteMessageResponse(event: WriteMessageResponseEvent) {
        showResponse("Write Message Response: ${event.response}")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }


    private fun showResponse(response: String) {
        binding.tvResponse.text = response
    }

}


/*
buktató az önállo feladatban:
asyncban pl thread előtt vagy után mérnek időt. hiba --> asyncon belül kell (új szál nem blokkol)

 */
