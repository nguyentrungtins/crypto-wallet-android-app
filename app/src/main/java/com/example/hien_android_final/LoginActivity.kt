package com.example.hien_android_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hien_android_final.databinding.ActivityHomeBinding
import com.example.hien_android_final.databinding.ActivityLoginBinding
import com.watasolutions.w3_databinding_wm.Constants
import com.watasolutions.w3_databinding_wm.MainViewModel



class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if(viewModel.checkLogin()) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            viewModel.checkEmailAndPassword(email, password)
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent);

        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtPassword.text.toString().trim()
                val intent = Intent(this, Home::class.java)
                val bundle = Bundle()
                bundle.putParcelable(Constants.KEY_USER, Student(email, password))
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

}