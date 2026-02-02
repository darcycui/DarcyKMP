package com.darcy.kmpdemo.ui.screen.learn

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
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnNavigation
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnRoute
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnPages
import org.jetbrains.compose.resources.stringResource

var isFirstLaunchHome = true

@Composable
fun ShowHome(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (LearnRoute) -> Unit = {}
) {
    val navHostController = LearnNavigation.learnNavController()
    LaunchedEffect(Unit) {
        // 首次打开默认跳转
        if (isFirstLaunchHome) {
            navHostController.navigate(LearnRoute.LoadData)
            isFirstLaunchHome = false
        }
    }
    // darcyRefactor: 可观察的状态列表
//    val pagesStateList = remember { mutableStateListOf<Pages>() }
    val pagesStateList = mutableStateListOf<LearnPages>()
    LearnPages.entries.forEachIndexed { index, it ->
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
    onNextButtonClicked: (LearnRoute) -> Unit = {}, page: LearnPages
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            when (page) {
                LearnPages.HomePage -> onNextButtonClicked(LearnRoute.Home)
                LearnPages.EncryptTextPage -> {
                    onNextButtonClicked(LearnRoute.EncryptText("this is params text1"))
                    // appLocalNavController.current.navigate(AppRoute.EncryptText("this is params text2"))
                }

                LearnPages.EncryptFilePage -> onNextButtonClicked(LearnRoute.EncryptFile)
                LearnPages.LoadResourcePage -> onNextButtonClicked(LearnRoute.LoadResource)
                LearnPages.LoadMokoResourcePage -> onNextButtonClicked(LearnRoute.LoadMokoResource)
                LearnPages.KtorHttpPage -> onNextButtonClicked(LearnRoute.KtorHttp)
                LearnPages.KtorWebsocketPage -> onNextButtonClicked(LearnRoute.KtorWebsocket)
                LearnPages.DownloadImagePage -> onNextButtonClicked(LearnRoute.DownloadImage)
                LearnPages.UploadImagePage -> onNextButtonClicked(LearnRoute.UploadImage)
                LearnPages.KtorWebSocketSTMOPPage -> onNextButtonClicked(LearnRoute.KtorWebSocketSTMOP)
                LearnPages.NavigationRailPage -> onNextButtonClicked(LearnRoute.NavigationRail)
                LearnPages.UnknownPage -> onNextButtonClicked(LearnRoute.Unknown)
                LearnPages.CustomDrawPage -> onNextButtonClicked(LearnRoute.CustomDraw)
                LearnPages.NumberCardPage -> onNextButtonClicked(LearnRoute.NumberCard)
                LearnPages.LoadDataPage -> onNextButtonClicked(LearnRoute.LoadData)
            }
        }) {
        Text(
            text = stringResource(page.title),
//            style = MaterialTheme.typography.titleSmall,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}