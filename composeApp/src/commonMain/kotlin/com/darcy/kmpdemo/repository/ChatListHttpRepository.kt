package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.bean.http.ChatListResponse
import com.darcy.kmpdemo.bean.ui.ChatListItemBean
import kotlinx.coroutines.delay

class ChatListHttpRepository : IRepository {
    suspend fun getChatList(): ChatListResponse {
        delay(1_000)
        return ChatListResponse(
            items = listOf(
                ChatListItemBean(
                    id = 1,
                    title = "Tom",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747621605719.jpg",
                ),
                ChatListItemBean(
                    id = 2,
                    title = "Jerry",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623732775.jpg",
                ),
                ChatListItemBean(
                    id = 3,
                    title = "Lukas",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618388645.jpg",
                ),
                ChatListItemBean(
                    id = 4,
                    title = "SnowPrincess",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747622674941.jpg",
                ),
                ChatListItemBean(
                    id = 5,
                    title = "John",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747622760607.jpg",
                ),
                ChatListItemBean(
                    id = 6,
                    title = "Max",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623451248.jpg",
                ),
                ChatListItemBean(
                    id = 7,
                    title = "Mike",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623424292.jpg",
                ),
                ChatListItemBean(
                    id = 8,
                    title = "Mary",
                    subTitle = "最近一条消息",
                    avatar = "https://c-ssl.dtstatic.com/uploads/blog/202112/12/20211212103941_6754e.thumb.400_0.jpeg",
                ),
                ChatListItemBean(
                    id = 9,
                    title = "Lily",
                    subTitle = "最近一条消息",
                    avatar = "https://c-ssl.dtstatic.com/uploads/item/201911/14/20191114164501_HRAwR.thumb.400_0.jpeg",
                ),
                ChatListItemBean(
                    id = 10,
                    title = "Lucy",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618725927.jpg",
                ),
                ChatListItemBean(
                    id = 11,
                    title = "James",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747620516144.jpg",
                ),
                ChatListItemBean(
                    id = 12,
                    title = "Robert",
                    subTitle = "最近一条消息",
                    avatar = "https://img.52tiemo.com/uploads/allimg/2019080515/l5jpmzgjlie.jpg",
                ),
                ChatListItemBean(
                    id = 13,
                    title = "David",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618544825.jpg",
                ),
                ChatListItemBean(
                    id = 14,
                    title = "Michael",
                    subTitle = "最近一条消息",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747621887548.jpg",
                ),
                ChatListItemBean(
                    id = 15,
                    title = "Caroline",
                    subTitle = "最近一条消息",
                    avatar = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1422574704,3483347916&fm=253&gp=0.jpg",
                )
            )
        )
    }
}