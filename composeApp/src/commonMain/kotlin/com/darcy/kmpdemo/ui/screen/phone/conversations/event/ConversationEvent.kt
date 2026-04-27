package com.darcy.kmpdemo.ui.screen.phone.conversations.event

import com.darcy.kmpdemo.ui.base.IEvent

sealed class ConversationEvent : IEvent {
    data object GoChatPage : ConversationEvent()
}