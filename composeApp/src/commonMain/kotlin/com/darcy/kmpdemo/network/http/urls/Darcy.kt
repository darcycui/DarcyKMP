package com.darcy.kmpdemo.network.http.urls

import com.darcy.kmpdemo.network.http.urls.Hosts.DOMAIN

object Darcy {
//    const val HOME_URL = "https://$DOMAIN"
    const val HOME_URL = "http://$DOMAIN"

    const val LOGIN_URL = "$HOME_URL/api/login"
    const val REGISTER_URL = "$HOME_URL/api/register"
    const val SEARCH_FRIEND_URL = "$HOME_URL/api/users/query/phone"
    const val APPLY_FRIEND_URL = "$HOME_URL/api/friend-requests/create"
    const val QUERY_FRIEND_FROM_URL = "$HOME_URL/api/friend-requests/query/from"
    const val QUERY_FRIEND_TO_URL = "$HOME_URL/api/friend-requests/query/to"
    const val ACCEPT_FRIEND_URL = "$HOME_URL/api/friend-requests/accept"
    const val FRIENDSHIP_LIST_URL = "$HOME_URL/api/friendships/query/all"
    const val CONVERSATION_LIST_URL = "$HOME_URL/api/conversations/query/all"
    const val CONVERSATION_CREATE_URL = "$HOME_URL/api/conversations/create"

    const val QUERY_PRIVATE_MESSAGE_URL = "$HOME_URL/api/private-messages/query/page"
}