package com.example.hien_android_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hien_android_final.databinding.ActivityLoginBinding
import com.example.hien_android_final.databinding.ActivityRegisterBinding
import com.watasolutions.w3_databinding_wm.DataStorage
import com.watasolutions.w3_databinding_wm.MainViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if(viewModel.checkLogin()) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)

        }
        binding.btnEnter.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            viewModel.registerCheck(email, password)
        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtPassword.text.toString().trim()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                DataStorage.userData.add(mutableMapOf("email" to email, "password" to password))
            }
        }
    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
}