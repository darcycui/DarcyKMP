package com.darcy.kmpdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.darcy.kmpdemo.log.Loger
import com.darcy.kmpdemo.ui.theme.AppTheme
//import com.darcy.kmpdemo.network.ssl.SslSettings
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.darcy.kmpdemo.ui.screen.desktop.DesktopAppMainScreen
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnNavigation
import com.darcy.kmpdemo.ui.screen.phone.PhoneAppMainScreen
import com.darcy.kmpdemo.ui.screen.phone.navigation.AppNavigationNavHost
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhoneRoute

@Composable
@Preview
fun App() {
    // init actions
    LaunchedEffect(Unit) {
        Loger.initLogger()
//    SslCertsConfig()
    }
    val navController = rememberNavController()
    val startDestination = PhoneRoute.Login
    AppTheme {
        AppNavigationNavHost(navController, startDestination, Modifier.fillMaxSize())
    }

}

//@Composable
//fun SslCertsConfig() {
//    var bytesServer by remember {
//        mutableStateOf(ByteArray(0))
//    }
//    var bytesIP by remember {
//        mutableStateOf(ByteArray(0))
//    }
//    LaunchedEffect(Unit) {
//        logV("SslCertsConfig: init ssl certs")
//        bytesServer = Res.readBytes(SslSettings.KEYSTORE_PATH_SERVER)
//        bytesIP = Res.readBytes(SslSettings.KEYSTORE_PATH_IP)
//        SslSettings.initCertBytes(listOf(bytesServer, bytesIP))
//    }
//}