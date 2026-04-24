package com.darcy.kmpdemo.ui.screen.phone.add_friend.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class AddFriendEvent : IEvent {
    data object AddFriendSuccessEvent : AddFriendEvent()
}