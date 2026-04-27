package com.darcy.kmpdemo.ui.screen.phone.chat.privatechat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darcy.kmpdemo.bean.http.response.PrivateMessageResponse
import com.darcy.kmpdemo.bean.http.response.isSelfSent
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.page_chat
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneChatScreen() {
    PhoneChatInnerPage()
}

@Composable
private fun PhoneChatInnerPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(stringResource(Res.string.page_chat))
            PrivateMessageListComponent(listOf(), Modifier.fillMaxWidth().weight(1f))
            SendComponent()
        }
    }
}

@Composable
fun SendComponent(
    onSendClick: () -> Unit = {}
) {
    val textState = TextFieldState()
    Row(modifier = Modifier.height(40.dp)) {
        TextField(state = textState, modifier = Modifier.fillMaxHeight())
        Button(modifier = Modifier.fillMaxHeight(), onClick = onSendClick) {
            Text("发送")
        }
    }
}

@Composable
fun PrivateMessageListComponent(messageList: List<PrivateMessageResponse>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(messageList, key = { it.msgId }) { item ->
            if (item.isSelfSent()) {
                SendMessageComponent(item)
            } else {
                ReceiveMessageComponent(item)
            }
        }
    }

}

@Composable
fun ReceiveMessageComponent(item: PrivateMessageResponse) {
    TODO("Not yet implemented")
}

@Composable
fun SendMessageComponent(item: PrivateMessageResponse) {
    TODO("Not yet implemented")
}