package com.darcy.kmpdemo.bean.ui

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
    val text: String,
    val count: Int,
    val colors: List<Color>,
    val showNumberFloatDot: Boolean = false,
    val height: Dp = 50.dp,
    @param:FloatRange(from = 0.0, to = 1.0)
    val yPercent: Float
)

@Serializable
data class HistogramInfo(
    val text: String,
    val count: Int,
    @Transient
    val colors: List<Color> = listOf(Color.Cyan, Color.Blue),
    @param:FloatRange(from = 0.0, to = 1.0)
    val yPercent: Float = 0.3f
)