package com.darcy.kmpdemo.ui.screen.phone.navigation

import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.icon_home
import kmpdarcydemo.composeapp.generated.resources.icon_mine
import kmpdarcydemo.composeapp.generated.resources.icon_notification
import kmpdarcydemo.composeapp.generated.resources.icon_search
import kmpdarcydemo.composeapp.generated.resources.page_chat_list
import kmpdarcydemo.composeapp.generated.resources.page_dynamic
import kmpdarcydemo.composeapp.generated.resources.page_friends
import kmpdarcydemo.composeapp.generated.resources.page_mine
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class PhonePages(
    val title: StringResource,
    val icon: DrawableResource,
    val route: PhoneRoute = PhoneRoute.Default
) {
    ChatList(Res.string.page_chat_list, Res.drawable.icon_home, PhoneRoute.ChatList),
    Friends(Res.string.page_friends, Res.drawable.icon_search, PhoneRoute.Friends),
    Dynamic(Res.string.page_dynamic, Res.drawable.icon_notification, PhoneRoute.Dynamic),
    Mine(Res.string.page_mine, Res.drawable.icon_mine, PhoneRoute.Mine)
}

@Serializable
sealed class PhoneRoute() {
    @Serializable
    object Default : PhoneRoute()

    @Serializable
    object ChatList : PhoneRoute()

    @Serializable
    object Friends : PhoneRoute()

    @Serializable
    object Dynamic : PhoneRoute()

    @Serializable
    object Mine : PhoneRoute()

    @Serializable
    object Chat : PhoneRoute()
}