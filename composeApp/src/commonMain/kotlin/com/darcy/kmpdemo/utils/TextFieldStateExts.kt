package com.darcy.kmpdemo.utils

import androidx.compose.foundation.text.input.TextFieldState

fun TextFieldState.toLong():Long {
    return this.text.toString().trim().toLongOrNull() ?: 0
}