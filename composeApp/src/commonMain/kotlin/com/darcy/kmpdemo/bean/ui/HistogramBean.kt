package com.darcy.kmpdemo.bean.ui

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class HistogramBean(
    val items: List<HistogramItemBean>,
    val xAxisBean: AxisBean,
    val yAxisBean: AxisBean,
)

data class AxisBean(
    val divisionCount: Int,
    val divisionPeriod: Float,
    val showDivisionLine: Boolean,
    val showDivisionText: Boolean,
    val showDivisionTextFloatDot: Boolean = false,
)

data class HistogramItemBean(
    val name: String,
    val number: Float,
    val colors: List<Color>,
    val showNumberFloatDot: Boolean = false,
    val height: Dp = 50.dp,
    @param:FloatRange(from = 0.0, to = 1.0)
    val yPercent: Float
)