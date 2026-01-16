package com.darcy.kmpdemo.ui.navigation

import com.darcy.kmpdemo.log.logD
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.custom_draw
import kmpdarcydemo.composeapp.generated.resources.download_image
import kmpdarcydemo.composeapp.generated.resources.encrypt_file
import kmpdarcydemo.composeapp.generated.resources.encrypt_text
import kmpdarcydemo.composeapp.generated.resources.home
import kmpdarcydemo.composeapp.generated.resources.ktor_http
import kmpdarcydemo.composeapp.generated.resources.ktor_websocket
import kmpdarcydemo.composeapp.generated.resources.ktor_websocket_stomp
import kmpdarcydemo.composeapp.generated.resources.load_moko_resource
import kmpdarcydemo.composeapp.generated.resources.load_resource
import kmpdarcydemo.composeapp.generated.resources.navigation_rail
import kmpdarcydemo.composeapp.generated.resources.number_card
import kmpdarcydemo.composeapp.generated.resources.unknown
import kmpdarcydemo.composeapp.generated.resources.upload_image
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
enum class Pages(
    val title: StringResource,
    val routeName: String,
) {
    UnknownPage(title = Res.string.unknown, routeName = "UnknownPage"),
    HomePage(title = Res.string.home, routeName = "Home"),
    EncryptTextPage(
        title = Res.string.encrypt_text,
        routeName = "EncryptText"
    ),
    EncryptFilePage(
        title = Res.string.encrypt_file,
        routeName = "EncryptFile"
    ),
    LoadResourcePage(
        title = Res.string.load_resource,
        routeName = "LoadResource"
    ),
    LoadMokoResourcePage(
        title = Res.string.load_moko_resource,
        routeName = "LoadMokoResource"
    ),
    KtorHttpPage(
        title = Res.string.ktor_http,
        routeName = "KtorHttp"
    ),
    KtorWebsocketPage(
        title = Res.string.ktor_websocket,
        routeName = "KtorWebsocket"
    ),
    DownloadImagePage(
        title = Res.string.download_image,
        routeName = "DownloadImage"
    ),
    UploadImagePage(
        title = Res.string.upload_image,
        routeName = "UploadImage"
    ),
    KtorWebSocketSTMOPPage(
        title = Res.string.ktor_websocket_stomp,
        routeName = "KtorWebSocketSTMOP"
    ),
    NavigationRailPage(
        title = Res.string.navigation_rail,
        routeName = "NavigationRail"
    ),
    CustomDrawPage(
        title = Res.string.custom_draw,
        routeName = "CustomDraw"
    ),
    NumberCardPage(
        title = Res.string.number_card,
        routeName = "NumberCard"
    ),
    ;

    companion object {
        fun findPageByRoute(routeName: String): Pages {
            val route = routeName.substringAfterLast(".").split("/")[0]
            logD("findPageByRoute $routeName")
            return entries.firstOrNull() {
                it.routeName == route
            } ?: UnknownPage
        }
    }
}


sealed class AppRoute {
    @Serializable
    object Unknown : AppRoute()

    @Serializable
    object Home : AppRoute()

    @Serializable
    data class EncryptText(val text: String) : AppRoute()

    @Serializable
    object EncryptFile : AppRoute()

    @Serializable
    object LoadResource : AppRoute()

    @Serializable
    object LoadMokoResource : AppRoute()

    @Serializable
    object KtorHttp : AppRoute()

    @Serializable
    object KtorWebsocket : AppRoute()

    @Serializable
    object DownloadImage : AppRoute()

    @Serializable
    object UploadImage : AppRoute()

    @Serializable
    object KtorWebSocketSTMOP : AppRoute()

    @Serializable
    object NavigationRail : AppRoute()

    @Serializable
    object CustomDraw : AppRoute()
    @Serializable
    object NumberCard : AppRoute()
}

