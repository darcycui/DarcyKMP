package com.darcy.kmpdemo.repository

import com.darcy.kmpdemo.bean.http.FriendsResponse
import com.darcy.kmpdemo.bean.ui.FriendsItemBean
import kotlinx.coroutines.delay

class FriendsHttpRepository : IRepository {
    suspend fun getFriendsList(): FriendsResponse {
        delay(1_000)
        return FriendsResponse(
            items = listOf(
                FriendsItemBean(
                    id = 1,
                    name = "Tom",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747621605719.jpg",
                ),
                FriendsItemBean(
                    id = 2,
                    name = "Jerry",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623732775.jpg",
                ),
                FriendsItemBean(
                    id = 3,
                    name = "Lukas",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618388645.jpg",
                ),
                FriendsItemBean(
                    id = 4,
                    name = "SnowPrincess",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747622674941.jpg",
                ),
                FriendsItemBean(
                    id = 5,
                    name = "John",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747622760607.jpg",
                ),
                FriendsItemBean(
                    id = 6,
                    name = "Max",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623451248.jpg",
                ),
                FriendsItemBean(
                    id = 7,
                    name = "Mike",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747623424292.jpg",
                ),
                FriendsItemBean(
                    id = 8,
                    name = "Mary",
                    nickName = "",
                    avatar = "https://c-ssl.dtstatic.com/uploads/blog/202112/12/20211212103941_6754e.thumb.400_0.jpeg",
                ),
                FriendsItemBean(
                    id = 9,
                    name = "Lily",
                    nickName = "",
                    avatar = "https://c-ssl.dtstatic.com/uploads/item/201911/14/20191114164501_HRAwR.thumb.400_0.jpeg",
                ),
                FriendsItemBean(
                    id = 10,
                    name = "Lucy",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618725927.jpg",
                ),
                FriendsItemBean(
                    id = 11,
                    name = "James",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747620516144.jpg",
                ),
                FriendsItemBean(
                    id = 12,
                    name = "Robert",
                    nickName = "",
                    avatar = "https://img.52tiemo.com/uploads/allimg/2019080515/l5jpmzgjlie.jpg",
                ),
                FriendsItemBean(
                    id = 13,
                    name = "David",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747618544825.jpg",
                ),
                FriendsItemBean(
                    id = 14,
                    name = "Michael",
                    nickName = "",
                    avatar = "https://img.tuxiangyan.com/zb_users/upload/2023/04/202304061680747621887548.jpg",
                ),
                FriendsItemBean(
                    id = 15,
                    name = "Caroline",
                    nickName = "",
                    avatar = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1422574704,3483347916&fm=253&gp=0.jpg",
                )
            )
        )
    }
}