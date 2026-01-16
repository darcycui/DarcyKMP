package com.darcy.kmpdemo.bean.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class ArcRegionBean(
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
    val topLeft: Offset,
    val width: Dp,
    val height: Dp,
    val text: String,
)
