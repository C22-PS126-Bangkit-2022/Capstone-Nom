package com.bangkit.capstonenom.ui.activity.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bangkit.capstonenom.databinding.ActivityProfileBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private val user = Firebase.auth.currentUser as FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageProfile: ImageView = binding.ivProfile
        val emailProfile: TextView = binding.tvProfileEmail
        val idProfile: TextView = binding.tvProfileId

        auth = Firebase.auth
        val userProfile = auth.currentUser
        if (userProfile != null){
            imageProfile.setImageURI(userProfile.photoUrl)
            emailProfile.setText(userProfile.email.toString()).toString()
            idProfile.setText(userProfile.uid)
        }
    }


}