package com.darcy.kmpdemo.ui.screen.phone.add_friend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.components.structure.TipsDialog
import com.darcy.kmpdemo.ui.screen.phone.add_friend.intent.AddFriendIntent

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