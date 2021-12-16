package com.example.appnews.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class PassCodeViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext
    var checkPass: MutableLiveData<Boolean> = MutableLiveData()

    fun setPassWord(pass: String) {
        val share = context.getSharedPreferences("PassCode", Context.MODE_PRIVATE)
        val editor = share.edit()
        editor.putString("passcode", pass)
        editor.apply()
    }

    fun getPassword(): String {
        val share = context.getSharedPreferences("PassCode", Context.MODE_PRIVATE)
        val pass = share.getString("passcode", "")
        return pass!!
    }

    fun comparePass(userPass: String) {
        val pass = getPassword()
        checkPass.value = (userPass == pass)
    }
}