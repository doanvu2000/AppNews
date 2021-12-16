package com.example.appnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.appnews.R
import com.example.appnews.viewmodels.PassCodeViewModel
import kotlinx.android.synthetic.main.activity_change_pass_code.*


class ChangePassCodeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass_code)
        supportActionBar?.hide()
        onClickButton()
    }

    private fun onClickButton() {
        btnBack.setOnClickListener {
            finish()
        }
        btn0.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btnC.setOnClickListener{
            var passWord = edtEnterPass.text.toString()
            if (passWord.isNotEmpty())
                passWord = passWord.substring(0, passWord.length - 1)
            edtEnterPass.setText(passWord)
        }
        btnSetPass.setOnClickListener{
            val passWord = edtEnterPass.text.toString()
            if (passWord.length == 4) {
                val viewModel = ViewModelProvider(this)[PassCodeViewModel::class.java]
                viewModel.setPassWord(passWord)
                tvMessage.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                },2000)
            }
        }
    }

    override fun onClick(view: View) {
        val button = view as AppCompatButton
        var passWord = edtEnterPass.text.toString()
        if (passWord.isEmpty()) passWord = ""
        if (passWord.length < 4) {
            when (button.text.toString()) {
                "0" -> passWord += "0"
                "1" -> passWord += "1"
                "2" -> passWord += "2"
                "3" -> passWord += "3"
                "4" -> passWord += "4"
                "5" -> passWord += "5"
                "6" -> passWord += "6"
                "7" -> passWord += "7"
                "8" -> passWord += "8"
                "9" -> passWord += "9"
            }
            edtEnterPass.setText(passWord)
        }
    }
}