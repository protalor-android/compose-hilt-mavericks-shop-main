package com.shop.main

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull

fun SemanticsNode.findTextsByTags(vararg tags: String): List<String> {
    return children
        .flatMap { it.children }
        .filter { it.config.getOrNull(SemanticsProperties.TestTag) in tags }
        .mapNotNull { it.config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text }
}