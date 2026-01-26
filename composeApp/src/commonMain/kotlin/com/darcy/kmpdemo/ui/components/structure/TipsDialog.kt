package com.darcy.kmpdemo.ui.components.structure

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darcy.kmpdemo.ui.colors.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsDialog(
    titleStr: String = "",
    contentStr: String = "",
    code: Int = 0,
    confirmStr: String = "",
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        content = {
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(200.dp)
                    .background(
                        AppColors.bg_color_white,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Text(
                        text = titleStr,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = contentStr,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().basicMarquee(),
                        maxLines = 2,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "错误码: $code",
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = confirmStr,
                            modifier = Modifier.clickable {
                                onConfirm()
                            }
                        )
                    }
                }
            }
        },
    )
}

@Composable
@Preview
fun TipsDialogPreview() {
    TipsDialog(
        titleStr = "错误",
        contentStr = "加载失败\n加载失败",
        onDismissRequest = {},
        onConfirm = {}
    )
}