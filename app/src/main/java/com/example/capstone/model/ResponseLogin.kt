package com.example.capstone.modelimport android.os.Parcelableimport kotlinx.parcelize.Parcelize//@Parcelize//data class ResponseLogin(//    val error: Boolean,//    val message: String,//    val loginResult: ResponseLoginResult? = null//) : Parcelable////@Parcelize//data class ResponseLoginResult(//    val userId: String,//    val name: String,//    val token: String//) : Parcelable@Parcelizedata class ResponseLogin(    val msfg: String,    val status: Int,    val data: ResponseLoginResult? = null) : Parcelable@Parcelizedata class ResponseLoginResult(    val id: Int,    val name: String,    val token: String) : Parcelable