package com.darcy.kmpdemo.ui.screen.phone.add_friend

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.darcy.kmpdemo.bean.http.response.SearchUserResponse
import com.darcy.kmpdemo.bean.ui.UserItemBean
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.colors.AppColors
import com.darcy.kmpdemo.ui.components.structure.TipsDialog
import com.darcy.kmpdemo.ui.screen.phone.add_friend.intent.AddFriendIntent
import com.darcy.kmpdemo.ui.screen.phone.add_friend.state.AddFriendState
import com.darcy.kmpdemo.ui.screen.phone.mine.intent.MineIntent
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.icon_header_default
import kmpdarcydemo.composeapp.generated.resources.page_mine
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneAddFriendScreen() {
    val viewModel: AddFriendViewModel = viewModel(
        factory = AddFriendViewModel.Factory
    )
    PhoneAddFriendInnerPage(viewModel)
}

@Composable
fun PhoneAddFriendInnerPage(viewModel: AddFriendViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nameTextFieldState: TextFieldState by remember { mutableStateOf(TextFieldState("")) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchUserComponent(
                nameTextFieldState,
                viewModel
            )
            UserInfoComponent(uiState, viewModel)
        }

        if (uiState.tipsState.showTips) {
            uiState.tipsState.apply {
                TipsDialog(
                    titleStr = title,
                    contentStr = tips,
                    code = code,
                    confirmStr = middleButtonText,
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
}

@Preview
@Composable
private fun SearchUserComponent(
    nameTextFieldState: TextFieldState = TextFieldState(""),
    viewModel: AddFriendViewModel? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            TextField(
                state = nameTextFieldState,
                placeholder = {
                    Text(text = "请输入手机号")
                },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                val phone = nameTextFieldState.text.toString()
                viewModel?.dispatch(AddFriendIntent.ActionSearchUser(phone))
            }) {
                Text(text = "搜索")
            }
        }
    }
}


@Preview
@Composable
fun UserInfoComponent(
    uiState: AddFriendState = AddFriendState(),
    viewModel: AddFriendViewModel? = null
) {
    val user = uiState.userInfo
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(8.dp))
        AsyncImage(
            model = user.avatar.ifEmpty { Res.drawable.icon_header_default },
            contentDescription = stringResource(Res.string.page_mine),
            modifier = Modifier.size(40.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AppColors.color_9e82f0,
                            AppColors.color_42a5f5,
                        )
                    ),
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.username, modifier = Modifier.weight(1f))
        Button(onClick = {
            viewModel?.dispatch(AddFriendIntent.ActionApplyFriend(user.id))
        }) {
            Text(text = uiState.statusText)
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}