package sdk.chat.messenger.registerlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import com.example.workmate.R
import kotlinx.android.synthetic.main.activity_login1.*
import sdk.chat.messenger.messages.LatestMessagesActivity

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login1)

        login_button_login.setOnClickListener {
            performRegister()
        }

        back_to_register_textview.setOnClickListener{
            finish() //come out of the login activity and get back into the main activity
        }
    }
private fun performRegister(){
    val email = email_edittext_login.text.toString()
    val password = password_edittext_login.text.toString()

    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
        return
    }

    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener

                //else if user is created successfully

                val result = it.result  // Kotlin says result is of type "AuthResult?"
                if (result != null) {
                    val user = result.user   // Kotlin says user is of type "FirebaseUser?"
                    if (user != null) {
                        Log.d("Main", "Successfully created user with uid: ${user.uid}")
                    }
                }
                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        .addOnFailureListener {
            Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
        }
        }
}