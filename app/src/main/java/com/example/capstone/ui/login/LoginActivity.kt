package com.example.capstone.ui.login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.example.capstone.R
import com.example.capstone.ui.main.MainActivity
import com.example.capstone.data.Result
import com.example.capstone.databinding.ActivityLoginBinding
import com.example.capstone.factory.ViewModelFactory
import com.example.capstone.model.LoginResultModel
import com.example.capstone.model.ResponseLogin
import com.example.capstone.preference.PreferenceLogin
import com.example.capstone.ui.custom_view.AlertDialog
import com.example.capstone.ui.sign_up.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private  val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
        buttonLogin()
        setUi()

        binding.tvDaftarDisini.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun setUi(){
        supportActionBar?.hide()
    }

    private fun setViewModel(){
        viewModelFactory = ViewModelFactory.getInstnce(binding.root.context)
    }

    private fun buttonLogin(){
        binding.btnLogin.setOnClickListener {
            val email = binding.edtUsernameLogIn.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                processLogin(email, password)
            } else {
                Toast.makeText(this, "Lengkapi Form", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processLogin(email: String, password:String){
        loginViewModel.authLogin(email, password).observe(this){ result->
            if (result != null){
                when(result){
                    is Result.Error -> {
                        Toast.makeText(this, "${result.error}", Toast.LENGTH_SHORT).show()
                        errorLogin()
                    }
                    is Result.Success -> {
                        succesLogin(result.data)
                        Toast.makeText(this, "Selamat Datang ${result.data.data.toString()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun intentToHome(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun succesLogin(responseLogin: ResponseLogin){
        saveDataUser(responseLogin)
        intentToHome()
    }

    private fun errorLogin(){
        AlertDialog(this, R.string.error_message, R.drawable.ic_home)
    }

    private fun saveDataUser(responseLogin: ResponseLogin){
        val preferenceLogin = PreferenceLogin(this)
        val resultLogin = responseLogin.data
        val loginResultModel = LoginResultModel(
            name = resultLogin?.name, id =  resultLogin?.id.toString(), token = resultLogin?.token
        )
        preferenceLogin.setAuthLogin(loginResultModel)
    }
}