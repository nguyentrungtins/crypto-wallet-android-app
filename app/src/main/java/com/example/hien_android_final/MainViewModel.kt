package com.watasolutions.w3_databinding_wm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class Error {
    ERROR_EMAIL,
    ERROR_PASSWORD,
}

class Resp(val isSuccess: Boolean, val error: Error?)

class MainViewModel : ViewModel() {
    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    fun checkEmailAndPassword(email: String, password: String) {
        //kiem tra format email
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Email không hợp lệ")
            return
        }
        //password length > 8 && < 10
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("Password không hợp lệ")
            return
        }

        val findData = DataStorage.userData.find { userData -> userData["email"] == email }
        var _error = ""
        if (findData == null) {
            _error += "Sai Email hoặc mật khẩu, nếu bạn chưa có tài khoản vui lòng ấn Register!"
        }
        else
        {
            if(password != findData["password"])
                _error += "Mật khẩu không khớp!"
        }

        if(_error.isNotEmpty())
            return _isErrorEvent.postValue(_error)

        if (findData != null) {
            DataStorage.currentUser = findData
        }
        _isSuccessEvent.postValue(true)
    }
    fun checkLogin(): Boolean {
        val findData = DataStorage.currentUser
        Log.v("current user: ", findData.toString())
        if (findData == null || findData.isEmpty()) {
            return false
        }
        else return true
    }
    fun registerCheck(email: String, password: String) {
        //kiem tra format email
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Email không hợp lệ")
            return
        }
        //password length > 8 && < 10
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("Password không hợp lệ")
            return
        }

        //check existed account
        val isExisted = CheckExistEmail(email)
        if (!isExisted) {
            _isErrorEvent.postValue("Người dùng đã tồn tại, vui lòng đăng ký email khác!")
            return
        }

        _isSuccessEvent.postValue(true)
    }



    private fun isEmailValid(email: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true
        }
        return false

    }
    private fun isEmailMatchData(email: String): Boolean {
        if (email == "admin@gmail.com"){
            return true
        }
        return false

    }
    private fun isPasswordValid(password: String): Boolean {
        if (password.length in 8..10){
            return true
        }
        return false
    }

    private fun isPasswordMatchData(password: String): Boolean {
        if (password == "admin@gmail.com"){
            return true
        }
        return false

    }

    private fun CheckExistEmail(email: String): Boolean {
        val isExistEmail = DataStorage.userData.find { userData -> userData["email"] == email }
        if (isExistEmail == null) {
            return true
        }
        println(DataStorage.userData)
        return false
    }

}