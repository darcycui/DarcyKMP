package com.darcy.kmpdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.darcy.kmpdemo.log.Loger
import com.darcy.kmpdemo.ui.theme.AppTheme
//import com.darcy.kmpdemo.network.ssl.SslSettings
import androidx.compose.ui.tooling.preview.Preview
import com.darcy.kmpdemo.ui.screen.desktop.DesktopAppMainScreen
import com.darcy.kmpdemo.ui.screen.learn.navigation.LearnNavigation
import com.darcy.kmpdemo.ui.screen.phone.PhoneAppMainScreen

@Composable
@Preview
fun App() {
    // init actions
    LaunchedEffect( Unit){
        Loger.initLogger()
//    SslCertsConfig()
    }
    AppTheme {
//        LearnNavigation()
        PhoneAppMainScreen()
//        DesktopAppMainScreen()
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