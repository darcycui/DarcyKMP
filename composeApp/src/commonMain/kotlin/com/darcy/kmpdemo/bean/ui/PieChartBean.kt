package com.darcy.kmpdemo.bean.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

data class PieChartBean(
    val items: List<ArcRegion> = listOf(),
)

data class ArcRegion(
    val startAngle: Float,
    val sweepAngle: Float,
    val color: Color,
    val topLeft: Offset,
    val width: Dp,
    val height: Dp,
    val text: String,
    val count: Int
)

@Serializable
data class ArcRegionInfo(
    val text: String = "分类1",
    val count: Int = 1,
    @Transient
    val color: Color = Color.Cyan
) : Comparable<ArcRegionInfo> {
    override fun compareTo(other: ArcRegionInfo): Int {
        return this.count.compareTo(other.count)
    }
}

