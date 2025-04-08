package com.shop.main.util

import android.util.Log
import android.webkit.JavascriptInterface

class BrowserJSBridge(val onBack: () -> Unit) {

    @JavascriptInterface
    fun historyBack() {
        Log.e("BrowserJSBridge", "historyBack")
        onBack()
    }

}
