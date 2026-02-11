package com.darcy.kmpdemo.ui.screen.phone.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.darcy.kmpdemo.bean.ui.ChatListItemBean
import com.darcy.kmpdemo.storage.database.tables.ConversationEntity
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.storage.memory.IMGlobalStorage
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenStateIntent
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.colors.AppColors
import com.darcy.kmpdemo.ui.components.structure.TipsDialog
import com.darcy.kmpdemo.ui.screen.phone.chatlist.intent.ChatListIntent
import com.darcy.kmpdemo.ui.screen.phone.chatlist.state.ChatListState
import io.ktor.http.encodeURLPath
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.icon_header_default
import org.jetbrains.compose.resources.painterResource

@Composable
fun PhoneChatListScreen() {
    val viewModel: ChatListViewModel = viewModel(factory = ChatListViewModel.Factory)
    LaunchedEffect(Unit) {
        viewModel.dispatch(FetchIntent.ActionLoadData)
        IMGlobalStorage.setCurrentUser(
            UserEntity(
                1,
                "Darcy",
                "https://avatars.githubusercontent.com/u/1020407?s=200&v=4"
            )
        )
    }
    PhoneChatListInnerPage(viewModel, Modifier.fillMaxSize())
}

@Composable
fun PhoneChatListInnerPage(
    viewModel: ChatListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState: ChatListState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState.screenState) {
            is ScreenState.Loading -> {
                Text(text = "加载中")
            }

            is ScreenState.Success -> {
                ShowSuccessPage(uiState, viewModel, modifier)
            }

            is ScreenState.Error -> {
                val errorState = uiState.screenState as ScreenState.Error
                TipsDialog(
                    titleStr = "错误",
                    contentStr = errorState.errorMessage,
                    code = errorState.errorCode,
                    confirmStr = "确定",
                    onDismissRequest = {
                        viewModel.dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Success))
                    },
                    onConfirm = {
                        viewModel.dispatch(ScreenStateIntent.ScreenStateChange(ScreenState.Success))
                    }
                )
            }

            else -> {
                Text(text = "其他")
            }
        }
        if (uiState.tipsState.showTips) {
            TipsDialog(
                titleStr = uiState.tipsState.title,
                contentStr = uiState.tipsState.tips,
                code = uiState.tipsState.code,
                confirmStr = uiState.tipsState.middleButtonText,
                onDismissRequest = {
                    viewModel.dispatch(TipsIntent.DismissTips)
                },
                onConfirm = {
                    viewModel.dispatch(TipsIntent.DismissTips)
                }
            )
        }
    }
}

@Composable
private fun ShowSuccessPage(
    uiState: ChatListState,
    viewModel: ChatListViewModel,
    modifier: Modifier = Modifier
) {
    var count: Long by remember { mutableStateOf(1) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier.weight(1f), onClick = {
                count++
                viewModel.dispatch(
                    ChatListIntent.ActionCreateConversation(
                        1,
                        count,
                        ConversationEntity(
                            conversationId = count,
                            name = "会话1",
                            avatar = "",
                            type = 1,
                        )
                    )
                )
            }) {
                Text(text = "增")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.dispatch(
                    ChatListIntent.ActionDeleteConversation(1)
                )
            }) {
                Text(text = "删")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.dispatch(
                    ChatListIntent.ActionUpdateConversation(
                        1,
                        ConversationEntity(
                            conversationId = 1,
                            name = "会话1修改-${RandomHelper.randomInt(100, 1000)}",
                            avatar = "",
                            type = 1,
                        )
                    )
                )
            }) {
                Text(text = "改")
            }
            Button(modifier = Modifier.weight(1.5f), onClick = {
                viewModel.dispatch(ChatListIntent.ActionQueryConversationsByUserId(IMGlobalStorage.getCurrentUserId()))
            }) {
                Text(text = "查1")
            }
            Button(modifier = Modifier.weight(1.5f), onClick = {
                viewModel.dispatch(ChatListIntent.ActionQueryUsersByConversationId(1))
            }) {
                Text(text = "查2")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(uiState.items.size, key = { uiState.items[it].id }) { index ->
                ChatListItem(uiState.items[index], Modifier)
            }
        }
    }
}


@Composable
private fun ChatListItem(
    bean: ChatListItemBean,
    modifier: Modifier
) {

    Column(modifier = Modifier.fillMaxWidth().height(68.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = bean.avatar.encodeURLPath(),
                placeholder = painterResource(Res.drawable.icon_header_default),
                error = painterResource(Res.drawable.icon_header_default),
                contentDescription = null,
                modifier = Modifier.size(48.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    Text(
                        text = bean.title,
                        fontSize = 16.sp,
                        color = AppColors.color_102c56,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = bean.subTitle,
                        fontSize = 14.sp,
                        color = AppColors.color_102c56,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Surface(
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .size(22.dp)
                        .offset(x = 0.dp, y = (-4).dp),
                    color = AppColors.text_color_red_fb6363,
                    shape = CircleShape
                ) {
                    Text(
                        text = "12",
                        fontSize = 10.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = AppColors.bg_color_white,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.wrapContentSize()
                            .align(Alignment.Center)
                            .offset(x = 0.dp, y = (-1).dp)
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.fillMaxWidth().height(0.5.dp)
                .background(AppColors.bg_color_green_13ce66)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xffffffff
)
@Composable
private fun ChatListItemPreview() {
    ChatListItem(
        bean = ChatListItemBean(
            id = 1,
            title = "标题标题标题标题标题",
            subTitle = "副标题副标题副标题副标题副标题副题副题副标题副标题副标题副",
            avatar = "https://picsum.photos/200/300"
        ),
        modifier = Modifier.fillMaxWidth()
    )
}