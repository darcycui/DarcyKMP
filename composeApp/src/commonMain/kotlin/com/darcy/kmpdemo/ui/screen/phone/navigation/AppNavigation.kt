package com.darcy.kmpdemo.ui.screen.phone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darcy.kmpdemo.ui.screen.desktop.DesktopAppMainScreen
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnNavigation
import com.darcy.kmpdemo.ui.screen.phone.PhoneAppMainScreen
import com.darcy.kmpdemo.ui.screen.phone.login.PhoneLoginScreen

object AppNavigation {

    // 定义全局 AppNavController
    private val appLocalNavController = staticCompositionLocalOf<NavHostController> {
        error("AppNavController not provided")
    }

    /**
     * 获取 CompositionLocal 用于初始化 AppNavController
     */
    fun getCompositionLocal(): ProvidableCompositionLocal<NavHostController> {
        return appLocalNavController
    }

    /**
     * 获取 AppNavController 用于页面跳转
     */
    @Composable
    fun navController(): NavHostController {
        return appLocalNavController.current
    }
}

@Composable
fun AppNavigationNavHost(
    navController: NavHostController,
    startDestination: PhoneRoute,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(AppNavigation.getCompositionLocal() provides navController) {
        NavHost(
            navController,
            startDestination = startDestination
        ) {
            composable<PhoneRoute.Login> {
                PhoneLoginScreen()
            }
            composable<PhoneRoute.AppMain> {
                PhoneAppMainScreen()
//                DesktopAppMainScreen()
//                LearnNavigation()
            }
        }
    }
}