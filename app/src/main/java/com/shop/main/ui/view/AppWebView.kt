package com.shop.main.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.webkit.WebSettings
import android.webkit.WebSettings.RenderPriority
import android.webkit.WebView
import android.webkit.WebViewClient
import com.shop.main.util.BrowserJSBridge

@SuppressLint("SetJavaScriptEnabled")
class AppWebView(context: Context): WebView(context) {
    private var onBackListener: OnBackListener? = null

    init {
        setBackgroundColor(Color.WHITE)
        webViewClient = WebViewClient()
        addJavascriptInterface(BrowserJSBridge(onBack = { onBackListener?.onBack() }), "MusinsaAppInterface")
        settings.useWideViewPort = true
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.loadsImagesAutomatically = true
        settings.setRenderPriority(RenderPriority.HIGH)
        settings.setSupportZoom(true)
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    fun setOnBackListener(onBackListener: OnBackListener) {
        this.onBackListener = onBackListener
    }

    interface OnBackListener {
        fun onBack()
    }
}