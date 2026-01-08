package com.darcy.kmpdemo.navigation

import kmpdarcydemo.composeapp.generated.resources.Res
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
import kmpdarcydemo.composeapp.generated.resources.unknown
import kmpdarcydemo.composeapp.generated.resources.upload_image
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
enum class Pages(val title: StringResource) {
    Unknown(title = Res.string.unknown),
    HomePage(title = Res.string.home),
    EncryptTextPage(title = Res.string.encrypt_text),
    EncryptFilePage(title = Res.string.encrypt_file),
    LoadResourcePage(title = Res.string.load_resource),
    LoadMokoResourcePage(title = Res.string.load_moko_resource),
    KtorHttpPage(title = Res.string.ktor_http),
    KtorWebsocketPage(title = Res.string.ktor_websocket),
    DownloadImagePage(title = Res.string.download_image),
    UploadImagePage(title = Res.string.upload_image),
    KtorWebSocketSTMOPPage(title = Res.string.ktor_websocket_stomp),
    NavigationRailPage(title = Res.string.navigation_rail),
}

sealed class PageParams {
}
