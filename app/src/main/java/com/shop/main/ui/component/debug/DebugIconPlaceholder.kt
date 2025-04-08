package com.shop.main.ui.component.debug

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun DebugIconPlaceholder(): ImageVector? {
    return if (LocalInspectionMode.current) {
        Icons.Default.Refresh
    } else null
}