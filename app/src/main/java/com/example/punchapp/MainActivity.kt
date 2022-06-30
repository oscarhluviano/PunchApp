package com.example.punchapp

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.punchapp.adapter.ViewPagerAdapter
import com.example.punchapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db:FirebaseFirestore
    private lateinit var auth:FirebaseAuth

    private var userType:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        var user = auth.currentUser?.uid
        db.collection("users").document(user.toString()).get().addOnSuccessListener {
            binding.tvPoints.text = it.get("points").toString()
            userType = it.get("type").toString()
        }

        binding.vPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.vPager){tab, index ->
            tab.text = when(index){
                0 -> {"Rewards"}
                1 -> {"Redeem"}
                2 -> {"History"}
                else -> throw Resources.NotFoundException("Position not found")
            }
        }.attach()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var user = auth.currentUser?.uid
        db.collection("users").document(user.toString()).get().addOnSuccessListener {
            userType = it.get("type").toString()
            if (userType == "1") {
                menuInflater.inflate(R.menu.main_menu, menu)
            }else if (userType == "0"){
                menuInflater.inflate(R.menu.admin_menu, menu)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.user_profile -> {
                val intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
            }
            R.id.add_reward -> {
                val intent = Intent(this, AddReward::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}