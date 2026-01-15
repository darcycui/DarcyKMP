package com.darcy.kmpdemo.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darcy.kmpdemo.ui.screen.ShowDownloadImage
import com.darcy.kmpdemo.ui.screen.ShowEncryptFile
import com.darcy.kmpdemo.ui.screen.ShowEncryptText
import com.darcy.kmpdemo.ui.screen.ShowHome
import com.darcy.kmpdemo.ui.screen.ShowKtorHttp
import com.darcy.kmpdemo.ui.screen.ShowKtorWebsocket
import com.darcy.kmpdemo.ui.screen.ShowLoadMokoResource
import com.darcy.kmpdemo.ui.screen.ShowLoadResource
import com.darcy.kmpdemo.platform.ShowUploadImage
import com.darcy.kmpdemo.ui.screen.ShowNavigationRailPage
import com.darcy.kmpdemo.ui.screen.ShowKtorWebsocketSTOMP

// 保存全局路由 appNavController
val appLocalNavController = staticCompositionLocalOf<NavController> {
    error("AppLocalNavController not provided")
}

@Composable
fun AppNavigation(
    // Creates the NavController
    navController: NavHostController = rememberNavController()
) {
    // backstack state
    val backStackEntry by navController.currentBackStackEntryAsState()
    // current screen
    val currentScreen =
        Pages.findPageByRoute(backStackEntry?.destination?.route ?: "")
    CompositionLocalProvider(appLocalNavController provides navController) {
        Scaffold(
            topBar = {
                AppBar(
                    currentScreen = currentScreen,
                    // show back button
                    canNavigateBack = navController.previousBackStackEntry != null,
                    // navigate up
                    navigateUp = navController::navigateUp
                )
            }
        ) { innerPadding ->
            // Creates the NavHost with the navigation graph consisting of supplied destinations
            NavHost(
                navController = navController,
                startDestination = AppRoute.Home,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable<AppRoute.Home> {
                    ShowHome(modifier = Modifier.padding(innerPadding)) { route ->
                        navController.navigate(route)
                    }
                }
                composable<AppRoute.EncryptText> {backStack->
                    val params = backStack.toRoute<AppRoute.EncryptText>()
                    ShowEncryptText(params.text)
                }
                composable<AppRoute.EncryptFile> { ShowEncryptFile() }
                composable<AppRoute.LoadResource> { ShowLoadResource() }
                composable<AppRoute.LoadMokoResource> { ShowLoadMokoResource() }
                composable<AppRoute.KtorHttp> { ShowKtorHttp() }
                composable<AppRoute.KtorWebsocket> { ShowKtorWebsocket() }
                composable<AppRoute.DownloadImage> { ShowDownloadImage() }
                composable<AppRoute.UploadImage> { ShowUploadImage() }
                composable<AppRoute.KtorWebSocketSTMOP> { ShowKtorWebsocketSTOMP() }
                composable<AppRoute.NavigationRail> { ShowNavigationRailPage() }
            }
        }
    }
}