package com.darcy.kmpdemo.ui.screen.phone.mine

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.darcy.kmpdemo.ui.colors.AppColors
import com.darcy.kmpdemo.utils.FileHelper
import com.darcy.kmpdemo.utils.PickHelper
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.icon_header_default
import kmpdarcydemo.composeapp.generated.resources.page_mine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.io.files.Path
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneMineScreen() {
    var headerPath: String by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = headerPath.ifEmpty { Res.drawable.icon_header_default },
                contentDescription = stringResource(Res.string.page_mine),
                modifier = Modifier.size(120.dp).align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
//                        color = AppColors.bg_color_green_13ce66,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                AppColors.color_9e82f0,
                                AppColors.color_42a5f5,
                            )
                        ),
                        shape = CircleShape
                    )
                    .clickable {
                        scope.launch {
                            val path = PickHelper.pickImage()
                            println("pick image: $path")
                            FileHelper.readFileBuffered(dealUriIfNeeded(path))
                            withContext(Dispatchers.Main) {
                                headerPath = path.toString()
                            }
                        }
                    }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Darcy",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(40.dp).align(Alignment.CenterHorizontally)
            ) {
                Button(modifier = Modifier.fillMaxHeight().weight(1f), onClick = {}) {
                    Text(text = "退出登录")
                }
            }
        }
    }
}