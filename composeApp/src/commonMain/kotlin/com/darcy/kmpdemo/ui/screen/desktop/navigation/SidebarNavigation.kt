package com.darcy.kmpdemo.ui.screen.desktop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.darcy.kmpdemo.ui.screen.phone.chatlist.PhoneChatListScreen
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darcy.kmpdemo.ui.screen.phone.dynamic.PhoneDynamicScreen
import com.darcy.kmpdemo.ui.screen.phone.friends.PhoneFriendsScreen
import com.darcy.kmpdemo.ui.screen.phone.mine.PhoneMineScreen
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhoneRoute

object SidebarNavigation {
    // 定义全局 SideNavController
    private val sideBarLocalNavController = staticCompositionLocalOf<NavHostController> {
        error("SideNavController not provided")
    }

    /**
     * 获取 CompositionLocal 用于初始化 SideNavController
     */
    fun getCompositionLocal(): ProvidableCompositionLocal<NavHostController> {
        return sideBarLocalNavController
    }

    /**
     * 获取 SideNavController 用于页面跳转
     */
    @Composable
    fun navController(): NavHostController {
        return sideBarLocalNavController.current
    }
}

@Composable
fun SidebarNavigationRailNavHost(
    navHostController: NavHostController,
    startPages: PhonePages,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(SidebarNavigation.getCompositionLocal() provides navHostController) {

        NavHost(
            navHostController,
            startDestination = startPages.route
        ) {
            composable<PhoneRoute.ChatList> {
                PhoneChatListScreen()
            }
            composable<PhoneRoute.Friends> {
                PhoneFriendsScreen()
            }
            composable<PhoneRoute.Dynamic> {
                PhoneDynamicScreen()
            }
            composable<PhoneRoute.Mine> {
                PhoneMineScreen()
            }
        }
    }

}

