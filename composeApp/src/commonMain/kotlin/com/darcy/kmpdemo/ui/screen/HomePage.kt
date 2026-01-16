package com.darcy.kmpdemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darcy.kmpdemo.ui.navigation.AppRoute
import com.darcy.kmpdemo.ui.navigation.Pages
import com.darcy.kmpdemo.ui.navigation.appLocalNavController
import org.jetbrains.compose.resources.stringResource

var isFirstLaunchHome = true

@Composable
fun ShowHome(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (AppRoute) -> Unit = {}
) {
    val navHostController = appLocalNavController.current
    LaunchedEffect(Unit) {
        // 首次打开默认跳转到 自定义绘制页面
        if (isFirstLaunchHome) {
            navHostController.navigate(AppRoute.CustomDraw)
            isFirstLaunchHome = false
        }
    }
    // darcyRefactor: 可观察的状态列表
//    val pagesStateList = remember { mutableStateListOf<Pages>() }
    val pagesStateList = mutableStateListOf<Pages>()
    Pages.entries.forEachIndexed { index, it ->
        if (index > 1) {
            pagesStateList.add(it)
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        // 垂直间距
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(pagesStateList.size) { index ->
            HomeItem(onNextButtonClicked = onNextButtonClicked, page = pagesStateList[index])
        }
    }
}

@Composable
fun HomeItem(
    onNextButtonClicked: (AppRoute) -> Unit = {}, page: Pages
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            when (page) {
                Pages.HomePage -> onNextButtonClicked(AppRoute.Home)
                Pages.EncryptTextPage -> {
                    onNextButtonClicked(AppRoute.EncryptText("this is params text1"))
                    // appLocalNavController.current.navigate(AppRoute.EncryptText("this is params text2"))
                }

                Pages.EncryptFilePage -> onNextButtonClicked(AppRoute.EncryptFile)
                Pages.LoadResourcePage -> onNextButtonClicked(AppRoute.LoadResource)
                Pages.LoadMokoResourcePage -> onNextButtonClicked(AppRoute.LoadMokoResource)
                Pages.KtorHttpPage -> onNextButtonClicked(AppRoute.KtorHttp)
                Pages.KtorWebsocketPage -> onNextButtonClicked(AppRoute.KtorWebsocket)
                Pages.DownloadImagePage -> onNextButtonClicked(AppRoute.DownloadImage)
                Pages.UploadImagePage -> onNextButtonClicked(AppRoute.UploadImage)
                Pages.KtorWebSocketSTMOPPage -> onNextButtonClicked(AppRoute.KtorWebSocketSTMOP)
                Pages.NavigationRailPage -> onNextButtonClicked(AppRoute.NavigationRail)
                Pages.UnknownPage -> onNextButtonClicked(AppRoute.Unknown)
                Pages.CustomDrawPage -> onNextButtonClicked(AppRoute.CustomDraw)
            }
        }) {
        Text(
            text = stringResource(page.title),
//            style = MaterialTheme.typography.titleSmall,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}