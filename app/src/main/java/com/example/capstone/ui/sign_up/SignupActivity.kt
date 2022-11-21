package com.example.capstone.ui.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.R
import com.example.capstone.databinding.ActivitySignupBinding
import com.example.capstone.ui.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMasukDisini.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}