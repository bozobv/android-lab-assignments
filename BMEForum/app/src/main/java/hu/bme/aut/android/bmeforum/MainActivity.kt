package hu.bme.aut.android.bmeforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import hu.bme.aut.android.bmeforum.databinding.ActivityMainBinding
import hu.bme.aut.android.bmeforum.extensions.validateNonEmpty

class MainActivity : BaseActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener { registerClick() }
        binding.btnLogin.setOnClickListener { loginClick() }
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun validateForm() = binding.etEmail.validateNonEmpty() && binding.etPassword.validateNonEmpty()


    private fun registerClick() {
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        firebaseAuth
            .createUserWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            .addOnSuccessListener { result ->
                hideProgressDialog()

                val firebaseUser = result.user
                val profileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(firebaseUser?.email?.substringBefore('@'))
                    .build()
                firebaseUser?.updateProfile(profileChangeRequest)

                toast("Registration successful")
            }
            .addOnFailureListener { exception ->
                hideProgressDialog()

                toast(exception.message)
            }
    }

    private fun loginClick() {
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        firebaseAuth
            .signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            .addOnSuccessListener {
                hideProgressDialog()

                startActivity(Intent(this@MainActivity, PostsActivity::class.java))
                finish()
            }
            .addOnFailureListener { exception ->
                hideProgressDialog()

                toast(exception.localizedMessage)
            }
    }


    /* private fun validateForm(): Boolean {
         if (binding.etEmail.text.isEmpty()) {
             binding.etEmail.error = "Required"
             return false
         }
         if (binding.etPassword.text.isEmpty()) {
             binding.etPassword.error = "Required"
             return false
         }
         return true
     }
 */
}