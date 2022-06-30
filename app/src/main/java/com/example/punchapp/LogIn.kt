package com.example.punchapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.punchapp.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)

        val intent = Intent(this, MainActivity::class.java)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if(currentUser != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setContentView(binding.root)

        val intentSignUp = Intent(this, SignUp::class.java)


        binding.btnLogIn.setOnClickListener {

            var email = binding.etLoginEmail.text.toString()
            var password = binding.etLoginPswd.text.toString()

            if(email != "" && password != "") {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            this.finish()
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                binding.etLoginEmail.error
                binding.etLoginPswd.error
            }
        }

        binding.btnSignUpGoogle.setOnClickListener {
            startActivity(intent)
        }

        binding.tvSignup.setOnClickListener {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intentSignUp)
        }


    }

    public override fun onStart() {
        super.onStart()
        val intent = Intent(this, MainActivity::class.java)
        // Check if user is signed in (non-null) and update UI accordingly.

    }
}