package com.example.capstone.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.databinding.FragmentProfileBinding
import com.example.capstone.model.LoginResultModel
import com.example.capstone.preference.PreferenceLogin
import com.example.capstone.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferenceLogin: PreferenceLogin
    private lateinit var loginResultModel: LoginResultModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        val root: View = binding.root
        preferenceLogin = PreferenceLogin(root.context)
        loginResultModel = preferenceLogin.getUser()

        buttonLogout()
        setupUi()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun setupUi() {
        binding.tvUsername.text = loginResultModel.name
        binding.tvEmail.text = loginResultModel.id
    }

    private fun buttonLogout(){
        binding.btnLogut.setOnClickListener {
            preferenceLogin.deleteUser()
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }

}