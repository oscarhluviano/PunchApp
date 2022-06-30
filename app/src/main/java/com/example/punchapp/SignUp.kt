package com.example.punchapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.punchapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LogIn::class.java)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val name = binding.etName.text
        val email = binding.etEmail.text
        val password = binding.etPswd.text

        binding.tvLogIn.setOnClickListener {
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            //Toast.makeText(this, name.toString()+"  "+email.toString(), Toast.LENGTH_SHORT).show()
            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val userDoc = hashMapOf(
                            "email" to email.toString(),
                            "name" to name.toString(),
                            "points" to "0",
                            "provider" to "email",
                            "type" to "1"
                        )
                        writeUserData(auth.uid.toString(),userDoc)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }



    fun writeUserData(user:String,userDoc:HashMap<String , String>) {
        db.collection("users").document(user)
            .set(userDoc)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
}