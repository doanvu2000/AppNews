package com.example.appnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.appnews.R
import kotlinx.android.synthetic.main.activity_detail_new.*

class DetailNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_new)
        supportActionBar?.hide()
        setLayout()
    }

    private fun setLayout() {
        val url = intent.getStringExtra("url")
        wvNew.webViewClient = WebViewClient()
        wvNew.clearCache(true)
        wvNew.clearHistory()
        wvNew.settings.javaScriptEnabled = true
        wvNew.settings.javaScriptCanOpenWindowsAutomatically = true
        if (url != null) {
            wvNew.loadUrl(url)
        }

    }
}