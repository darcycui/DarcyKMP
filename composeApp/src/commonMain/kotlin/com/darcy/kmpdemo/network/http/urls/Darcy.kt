package com.darcy.kmpdemo.network.http.urls

object Darcy {
//    const val HOME_URL = "https://darcycui.com.cn"
    const val HOME_URL = "http://10.0.0.241:7443"
//    const val HOME_URL = "http://192.168.0.103:7443"
    const val LOGIN_URL = "$HOME_URL/api/login"
    const val REGISTER_URL = "$HOME_URL/api/register"
    const val SEARCH_FRIEND_URL = "$HOME_URL/api/users/query/phone"
    const val ADD_FRIEND_URL = "$HOME_URL/api/friend-requests/create"
}