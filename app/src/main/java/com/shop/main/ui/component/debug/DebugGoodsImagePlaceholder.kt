package com.shop.main.ui.component.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import com.shop.main.R

@Composable
fun DebugGoodsImagePlaceholder(): Painter? {
    return if (LocalInspectionMode.current) {
        painterResource(id = R.drawable.sample_goods)
    } else null
}