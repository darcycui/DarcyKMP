package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.bean.http.ChatListResponse
import com.darcy.kmpdemo.bean.ui.ChatListItemBean
import kotlinx.coroutines.delay

class ChatListRepository : IRepository {
    suspend fun getChatList(): ChatListResponse {
        delay(1_000)
        return ChatListResponse(
            items = listOf(
                ChatListItemBean(
                    id = 1,
                    title = "Tom",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 2,
                    title = "Jerry",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 3,
                    title = "Lukas",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 4,
                    title = "SnowPrincess",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 5,
                    title = "John",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 6,
                    title = "Max",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 7,
                    title = "Mike",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 8,
                    title = "Mary",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 9,
                    title = "Lily",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 10,
                    title = "Lucy",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 11,
                    title = "James",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 12,
                    title = "Robert",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 13,
                    title = "David",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 14,
                    title = "Michael",
                    subTitle = "最近一条消息",
                    avatar = "",
                ),
                ChatListItemBean(
                    id = 15,
                    title = "Caroline",
                    subTitle = "最近一条消息",
                    avatar = "",
                )
            )
        )
    }
}