package com.darcy.kmpdemo.ui.screen.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.darcy.kmpdemo.log.logD
import com.example.myapplication.uiphone.navigation.BottomBarNavigationNavHost
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneAppMainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = PhonePages.ChatList
    PhoneAppMainInnerPage(navController, startDestination, modifier.fillMaxSize())
}

@Preview
@Composable
fun PhoneAppMainInnerPage(
    navController: NavHostController,
    startDestination: PhonePages,
    modifier: Modifier = Modifier
) {
    var currentPageIndex: Int by remember {
        mutableIntStateOf(startDestination.ordinal)
    }
    val pagerState =
        rememberPagerState(initialPage = currentPageIndex, pageCount = { PhonePages.entries.size })
    // 监听页面滑动
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { newPageIndex ->
            logD("滑动：newPageIndex: $newPageIndex currentPageIndex=$currentPageIndex")
            if (newPageIndex != currentPageIndex) {
                currentPageIndex = newPageIndex
            }
        }
    }
    // 监听底部导航点击
    LaunchedEffect(currentPageIndex) {
        logD("点击：currentPageIndex: $currentPageIndex")
        pagerState.scrollToPage(currentPageIndex)
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                PhonePages.entries.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentPageIndex == index,
                        onClick = {
                            navController.navigate(item.route) {
                                // 清除回退栈到根页面
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(item.icon),
                                contentDescription = stringResource(item.title)
                            )
                        },
                        label = {
                            Text(stringResource(item.title))
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BottomBarNavigationNavHost(navController, startDestination.route, pagerState, {
                currentPageIndex = it.ordinal
            })
        }
    }
}