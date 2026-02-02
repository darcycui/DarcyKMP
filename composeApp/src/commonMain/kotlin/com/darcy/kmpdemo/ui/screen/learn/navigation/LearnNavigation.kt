package com.darcy.kmpdemo.ui.screen.learn.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.darcy.kmpdemo.ui.screen.learn.ShowDownloadImage
import com.darcy.kmpdemo.ui.screen.learn.ShowEncryptFile
import com.darcy.kmpdemo.ui.screen.learn.ShowEncryptText
import com.darcy.kmpdemo.ui.screen.learn.ShowHome
import com.darcy.kmpdemo.ui.screen.learn.ShowKtorHttp
import com.darcy.kmpdemo.ui.screen.learn.ShowKtorWebsocket
import com.darcy.kmpdemo.ui.screen.learn.ShowLoadMokoResource
import com.darcy.kmpdemo.ui.screen.learn.ShowLoadResource
import com.darcy.kmpdemo.platform.ShowUploadImage
import com.darcy.kmpdemo.ui.screen.learn.ShowCustomDrawPage
import com.darcy.kmpdemo.ui.screen.learn.ShowNavigationRailPage
import com.darcy.kmpdemo.ui.screen.learn.ShowKtorWebsocketSTOMP
import com.darcy.kmpdemo.ui.screen.learn.ShowNumberCardPage
import com.darcy.kmpdemo.ui.screen.learn.loaddata.ShowLoadDataPage

object LearnNavigation {
    // 保存全局路由 appNavController
    private val learnLocalNavController = staticCompositionLocalOf<NavHostController> {
        error("AppLocalNavController not provided")
    }

    fun getLearnCompositionLocal() = learnLocalNavController

    // 获取全局路由 learnNavController
    @Composable
    fun learnNavController(): NavHostController {
        return learnLocalNavController.current
    }
}

@Composable
fun LearnNavigation(
    // Creates the NavController
    navController: NavHostController = rememberNavController()
) {
    // backstack state
    val backStackEntry by navController.currentBackStackEntryAsState()
    // current screen
    val currentScreen =
        LearnPages.findPageByRoute(backStackEntry?.destination?.route ?: "")
    CompositionLocalProvider(LearnNavigation.getLearnCompositionLocal() provides navController) {
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
                startDestination = LearnRoute.Home,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable<LearnRoute.Home> {
                    ShowHome(modifier = Modifier.padding(innerPadding)) { route ->
                        navController.navigate(route)
                    }
                }
                composable<LearnRoute.EncryptText> { backStack ->
                    val params = backStack.toRoute<LearnRoute.EncryptText>()
                    ShowEncryptText(params.text)
                }
                composable<LearnRoute.EncryptFile> { ShowEncryptFile() }
                composable<LearnRoute.LoadResource> { ShowLoadResource() }
                composable<LearnRoute.LoadMokoResource> { ShowLoadMokoResource() }
                composable<LearnRoute.KtorHttp> { ShowKtorHttp() }
                composable<LearnRoute.KtorWebsocket> { ShowKtorWebsocket() }
                composable<LearnRoute.DownloadImage> { ShowDownloadImage() }
                composable<LearnRoute.UploadImage> { ShowUploadImage() }
                composable<LearnRoute.KtorWebSocketSTMOP> { ShowKtorWebsocketSTOMP() }
                composable<LearnRoute.NavigationRail> { ShowNavigationRailPage() }
                composable<LearnRoute.CustomDraw> { ShowCustomDrawPage() }
                composable<LearnRoute.NumberCard> { ShowNumberCardPage() }
                composable<LearnRoute.LoadData> { ShowLoadDataPage() }
            }
        }
    }
}

fun NavHostController.learnNavigate(
    route: LearnRoute,
    clearStack: Boolean = true,
    includeRoot: Boolean = true
) {
    this.navigate(route){
        if (clearStack) {
            popUpTo(0) {
                inclusive = includeRoot
            }
        }
    }
}