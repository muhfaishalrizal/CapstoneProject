package com.example.capstone.ui.loginimport androidx.lifecycle.ViewModelimport com.example.capstone.data.AppRepositoryclass LoginViewModel(private val appRepository: AppRepository):ViewModel() {    fun authLogin(email: String, password:String) = appRepository.autLogin(email, password)}