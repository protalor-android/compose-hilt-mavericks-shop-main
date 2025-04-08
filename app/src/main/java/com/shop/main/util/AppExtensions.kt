package com.shop.main.util

import androidx.compose.runtime.Composable


internal fun Int.toCommaString(): String {
    return try {
        "%,d".format(this)
    } catch (e: Exception) {
        this.toString()
    }
}

internal fun String?.useNonBreakingSpace() = this.orEmpty().replace(' ', '\u00A0')

@Composable
internal fun Boolean.ShowIfTrue(content: @Composable () -> Unit) = run { if (this) content() }

@Composable
internal fun Boolean.ResolveShow(trueContent: @Composable () -> Unit, falseContent: @Composable () -> Unit) = run {
    if (this) trueContent() else falseContent()
}
