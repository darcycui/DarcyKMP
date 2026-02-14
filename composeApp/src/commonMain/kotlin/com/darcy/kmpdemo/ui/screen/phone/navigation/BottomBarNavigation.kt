package com.darcy.kmpdemo.ui.screen.phone.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darcy.kmpdemo.ui.screen.phone.chatlist.PhoneChatListScreen
import com.darcy.kmpdemo.ui.screen.phone.dynamic.PhoneDynamicScreen
import com.darcy.kmpdemo.ui.screen.phone.friends.PhoneFriendsScreen
import com.darcy.kmpdemo.ui.screen.phone.mine.PhoneMineScreen

object BottomBarNavigation {
    // 定义全局 BottomNavController
    private val bottomBarLocalNavController = staticCompositionLocalOf<NavHostController> {
        error("BottomNavController not provided")
    }

    /**
     * 获取 CompositionLocal 用于初始化 BottomNavController
     */
    fun getCompositionLocal(): ProvidableCompositionLocal<NavHostController> {
        return bottomBarLocalNavController
    }

    /**
     * 获取 BottomNavController 用于页面跳转
     */
    @Composable
    fun navController(): NavHostController {
        return bottomBarLocalNavController.current
    }
}

@Composable
fun BottomBarNavigationNavHost(
    navController: NavHostController,
    startDestination: PhoneRoute,
    pagerState: PagerState,
    onNavigation: (PhonePages) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        userScrollEnabled = true,
        beyondViewportPageCount = 3 // 缓存额外页面数3 即：一共加载4个页面
    ) { page ->
        // 显示对应页面
        when (PhonePages.entries[page]) {
            PhonePages.ChatList -> PhoneChatListScreen()
            PhonePages.Friends -> PhoneFriendsScreen()
            PhonePages.Dynamic -> PhoneDynamicScreen()
            PhonePages.Mine -> PhoneMineScreen()
        }
    }
    // 将 NavController 添加到 LocalNavController
    CompositionLocalProvider(BottomBarNavigation.getCompositionLocal() provides navController) {
        NavHost(
            navController,
            startDestination = startDestination
        ) {
            // 不显示页面(会与HorizontalPager重复) 只触发回调
            composable<PhoneRoute.ChatList> {
//                HomePage()
                onNavigation(PhonePages.ChatList)
            }
            composable<PhoneRoute.Friends> {
//                ManagePage()
                onNavigation(PhonePages.Friends)
            }
            composable<PhoneRoute.Dynamic> {
//                UploadPage()
                onNavigation(PhonePages.Dynamic)
            }
            composable<PhoneRoute.Mine> {
//                MinePage()
                onNavigation(PhonePages.Mine)
            }
        }
    }
}