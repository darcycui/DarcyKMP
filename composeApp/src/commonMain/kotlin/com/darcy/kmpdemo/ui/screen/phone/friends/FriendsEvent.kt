package com.darcy.kmpdemo.ui.screen.phone.friends

import com.darcy.kmpdemo.ui.base.IEvent

sealed class FriendsEvent : IEvent {
    data object GoAddFriend : FriendsEvent()
}