package com.example.tptour.attractions.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tptour.R


class WebViewActivity : AppCompatActivity() {
    companion object {
        private const val OFFICIAL_SITE = "official_site"

        fun newIntent(context: Context, link: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(OFFICIAL_SITE, link)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val webView = findViewById<WebView>(R.id.web_view)
        webView.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                url?.let {
                    view.loadUrl(url)
                }
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.useWideViewPort = true
        val url = intent.getStringExtra(OFFICIAL_SITE)
        url?.let {
            webView.loadUrl(it)
        }
        findViewById<ImageButton>(R.id.web_view_close_btn).setOnClickListener {
            finish()
        }
        findViewById<TextView>(R.id.web_title_text).text = url
    }
}