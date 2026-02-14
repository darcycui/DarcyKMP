package com.darcy.kmpdemo.ui.screen.phone.login

import RandomHelper
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import com.darcy.kmpdemo.bean.ui.UserItemBean
import com.darcy.kmpdemo.storage.database.tables.UserEntity
import com.darcy.kmpdemo.ui.colors.AppColors
import com.darcy.kmpdemo.ui.screen.phone.login.event.LoginEvent
import com.darcy.kmpdemo.ui.screen.phone.login.intent.LoginIntent
import com.darcy.kmpdemo.ui.screen.phone.login.state.LoginState
import com.darcy.kmpdemo.ui.screen.phone.navigation.AppNavigation
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhoneRoute
import com.darcy.kmpdemo.ui.screen.phone.navigation.customNavigate
import com.darcy.kmpdemo.utils.toLong
import io.ktor.http.encodeURLPath
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.icon_header_default
import kmpdarcydemo.composeapp.generated.resources.page_login
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneLoginScreen() {
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val appNavController = AppNavigation.navController()
    LaunchedEffect(Unit) {
        launch {
            viewModel.event.collect {
                when (it) {
                    is LoginEvent.LoginSuccessEvent -> {
                        appNavController.customNavigate(
                            route = PhoneRoute.AppMain, clearStack = true, includeRoot = true
                        )
                    }
                }
            }
        }

    }
    PhoneLoginInnerPage(viewModel)
}

@Composable
fun PhoneLoginInnerPage(viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val textFieldState: TextFieldState by remember { mutableStateOf(TextFieldState("1")) }
    Column(modifier = Modifier.fillMaxSize()) {
        LoginComponent(textFieldState, viewModel)
        CreateUserComponent(viewModel)
        Box(modifier = Modifier.fillMaxSize()) {
            UserListComponent(uiState, viewModel, Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun LoginComponent(
    textFieldState: TextFieldState,
    viewModel: LoginViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            state = textFieldState,
            placeholder = { Text("登录用户id") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val userId = textFieldState.toLong()
            viewModel.dispatch(
                LoginIntent.ActionLogin(
                    UserEntity(
                        userId, "user$userId", "昵称$userId",
                        age = 18,
                        sex = 1,
                        avatar = "https://avatars.githubusercontent.com/u/1021672"
                    )
                )
            )
        }) {
            Text(stringResource(Res.string.page_login))
        }
    }
}

@Composable
private fun CreateUserComponent(viewModel: LoginViewModel) {
    val textFieldState: TextFieldState by remember { mutableStateOf(TextFieldState("")) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("创建用户")
            TextField(
                state = textFieldState,
                placeholder = { Text(text = "新用户id") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Button(modifier = Modifier.weight(1f), onClick = {
                val userId = textFieldState.toLong()
                viewModel.dispatch(
                    LoginIntent.ActionAddUser(
                        UserEntity(
                            userId = userId,
                            name = "Darcy$userId",
                            age = 18,
                            sex = 1,
                            avatar = "https://avatars.githubusercontent.com/u/1021672"
                        )
                    )
                )
            }) {
                Text(text = "增")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.dispatch(LoginIntent.ActionDeleteUser(1))
            }) {
                Text(text = "删")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                val userId = textFieldState.toLong()
                viewModel.dispatch(
                    LoginIntent.ActionUpdateUser(
                        userId,
                        "DarcyUpdate${RandomHelper.randomInt(100, 999)}"
                    )
                )
            }) {
                Text(text = "改")
            }
            Button(modifier = Modifier.weight(1.5f), onClick = {
                viewModel.dispatch(LoginIntent.ActionQueryUserList)
            }) {
                Text(text = "查All")
            }
        }
    }
}

@Composable
fun UserListComponent(uiState: LoginState, viewModel: LoginViewModel, modifier: Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(uiState.items.size, key = { index -> uiState.items[index] }) { index ->
            UserListItem(uiState.items[index])
        }
    }
}

@Composable
private fun UserListItem(
    bean: UserItemBean,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxWidth().height(50.dp)) {
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
                modifier = Modifier.size(40.dp).clip(CircleShape).border(
                    width = 1.dp,
                    color = AppColors.bg_color_gray_f0f0f0,
                    shape = CircleShape
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "${bean.id}: ${bean.name}",
                fontSize = 16.sp,
                color = AppColors.color_102c56,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)
            )
        }
        Spacer(
            modifier = Modifier.fillMaxWidth().height(0.5.dp)
                .background(AppColors.bg_color_gray_f0f0f0)
        )
    }
}

