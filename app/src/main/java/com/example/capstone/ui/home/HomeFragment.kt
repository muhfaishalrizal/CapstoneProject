package com.example.capstone.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.R
import com.example.capstone.adapter.GreeventsAdapter
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.databinding.FragmentProfileBinding
import com.example.capstone.factory.ViewModelFactory
import com.example.capstone.model.LoginResultModel
import com.example.capstone.preference.PreferenceLogin

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var greeventsAdapter: GreeventsAdapter
    private lateinit var preferenceLogin: PreferenceLogin
    private lateinit var loginResultModel: LoginResultModel
    private lateinit var viewModelFactory: ViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val root: View = binding.root

        preferenceLogin = PreferenceLogin(binding.root.context)
        loginResultModel = preferenceLogin.getUser()
        setViewModel()
        setRecylerView(binding.root.context)
        getAllEvents()
        getUsernameUser()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun getUsernameUser() {
        binding.tvUsername.text = loginResultModel.name
    }

    private fun setViewModel(){
        viewModelFactory = ViewModelFactory.getInstnce(binding.root.context)
    }

    private fun setRecylerView(context: Context){
        binding.rvEvents.apply {
            greeventsAdapter = GreeventsAdapter()
            adapter = greeventsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getAllEvents(){
        homeViewModel.getAllEvents.observe(viewLifecycleOwner){
            greeventsAdapter.submitData(lifecycle, it)
        }
    }

}