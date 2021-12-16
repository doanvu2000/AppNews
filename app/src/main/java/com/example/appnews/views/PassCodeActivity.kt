package com.example.appnews.views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.appnews.R
import com.example.appnews.viewmodels.PassCodeViewModel
import kotlinx.android.synthetic.main.activity_pass_code.*
import kotlinx.android.synthetic.main.activity_pass_code.btn0
import kotlinx.android.synthetic.main.activity_pass_code.btn1
import kotlinx.android.synthetic.main.activity_pass_code.btn2
import kotlinx.android.synthetic.main.activity_pass_code.btn3
import kotlinx.android.synthetic.main.activity_pass_code.btn4
import kotlinx.android.synthetic.main.activity_pass_code.btn5
import kotlinx.android.synthetic.main.activity_pass_code.btn6
import kotlinx.android.synthetic.main.activity_pass_code.btn7
import kotlinx.android.synthetic.main.activity_pass_code.btn8
import kotlinx.android.synthetic.main.activity_pass_code.btn9
import kotlinx.android.synthetic.main.activity_pass_code.btnC
import kotlinx.android.synthetic.main.activity_pass_code.btnOK
import kotlinx.android.synthetic.main.activity_pass_code.edtEnterPass
import java.time.LocalDateTime

private const val TAG = "PassCodeActivity"

class PassCodeActivity : AppCompatActivity(), View.OnClickListener {
    private var checkPassWord = false
    lateinit var viewModel: PassCodeViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_code)
        supportActionBar?.hide()
        val time = LocalDateTime.now()
        tvTime.text = "${time.dayOfWeek.name} - ${time.dayOfMonth}/${time.month.value}/${time.year}"
        viewModel = ViewModelProvider(this)[PassCodeViewModel::class.java]
        onClickButton()
    }
    private fun onClickButton() {
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
        btnOK.setOnClickListener {
            val passWord = edtEnterPass.text.toString()
            viewModel.checkPass.observe(this, {
                checkPassWord = it
            })
            viewModel.comparePass(passWord)
            if (checkPassWord) {
                startActivity(Intent(this, MainActivity::class.java))
                edtEnterPass.setText("")
                finish()
            } else {
                Toast.makeText(this, "Password is not correct", Toast.LENGTH_SHORT).show()
            }
        }
        btnC.setOnClickListener {
            val passWord = edtEnterPass.text.toString()
            if (passWord.isNotEmpty())
                edtEnterPass.setText(passWord.substring(0, passWord.length - 1))
        }
        btnSetting.setOnClickListener {
            edtEnterPass.setText("")
            startActivity(Intent(this, ChangePassCodeActivity::class.java))
        }

    }

    override fun onClick(view: View?) {

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