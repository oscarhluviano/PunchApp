package com.example.punchapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.punchapp.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserProfile : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        var user = auth.currentUser?.uid

        db.collection("users").document(user.toString()).get().addOnSuccessListener {
            binding.tvUserName.text = it.get("name").toString()
            binding.tvUserEmail.text = it.get("email").toString()
            binding.tvUserPoints.text = it.get("points").toString()
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LogIn::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}