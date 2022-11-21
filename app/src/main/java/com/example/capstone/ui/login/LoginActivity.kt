package com.example.capstone.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capstone.R
import com.example.capstone.databinding.ActivityLoginBinding
import com.example.capstone.ui.sign_up.SignupActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDaftarDisini.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}