package com.shop.main.ui.screen.detail

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.shop.main.test.TEST_TAG_DETAIL_SCREEN
import com.shop.main.ui.view.AppWebView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailScreen(
    detailUrl: String,
    navigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var webView: WebView? by remember { mutableStateOf(null) }

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            navigateBack()
        }
    }

    Scaffold(
        modifier = Modifier.testTag(TEST_TAG_DETAIL_SCREEN)
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AndroidView(
                factory = {
                    AppWebView(it).apply {
                        setOnBackListener(object: AppWebView.OnBackListener {
                            override fun onBack() {
                                // 메인 스레드에서 navigateBack() 호출
                                coroutineScope.launch(Dispatchers.Main) {
                                    if (canGoBack()) {
                                        goBack()
                                    } else {
                                        navigateBack()
                                    }
                                }
                            }
                        })

                        webView = this
                        loadUrl(detailUrl)
                    }
                },
                update = { webView ->
                    webView.loadUrl(detailUrl)
                },
            )
        }
    }

}