package com.example.myapplication.uiphone.navigation

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
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhoneRoute

object BottomBarNavigation {
    // 定义全局 SideNavController
    private val bottomBarLocalNavController = staticCompositionLocalOf<NavHostController> {
        error("SideNavController not provided")
    }

    /**
     * 获取 CompositionLocal 用于初始化 SideNavController
     */
    fun getBottomBarCompositionLocal(): ProvidableCompositionLocal<NavHostController> {
        return bottomBarLocalNavController
    }

    /**
     * 获取 SideNavController 用于页面跳转
     */
    @Composable
    fun bottomBarNavController(): NavHostController {
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
        userScrollEnabled = true
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
    CompositionLocalProvider(BottomBarNavigation.getBottomBarCompositionLocal() provides navController) {
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

/**
 * 返回上一页
 */
fun NavHostController.bottomBarGoBack() {
    this.popBackStack()
}

/**
 * 跳转到指定页面
 */
fun NavHostController.bottomBarNavigate(
    route: PhoneRoute,
    clearStack: Boolean = true,
    includeRoot: Boolean = true
) {
    this.navigate(route) {
        if (clearStack) {
            popUpTo(0) {
                inclusive = includeRoot
            }
        }
    }
}

/**
 * 清空回退栈
 */
fun NavHostController.bottomBarClearStack(route: PhoneRoute) {
    this.clearBackStack<PhoneRoute>()
}