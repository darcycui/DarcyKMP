package com.darcy.kmpdemo.ui.screen.loaddata

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darcy.kmpdemo.ui.base.ScreenStatus
import com.darcy.kmpdemo.ui.screen.loaddata.intent.LoadDataIntent
import com.darcy.kmpdemo.ui.screen.loaddata.viewmodel.LoadDataViewModel

@Composable
fun ShowLoadDataPage() {
    val viewmodel: LoadDataViewModel = viewModel(factory = LoadDataViewModel.Factory)
    InnerLoadDataPage(viewmodel, Modifier)
}

@Composable
fun InnerLoadDataPage(
    viewModel: LoadDataViewModel,
    modifier: Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier.fillMaxSize()) {
        when (uiState.screenState) {
            is ScreenStatus.Initial -> {
                ShowLoading()
                viewModel.dispatch(LoadDataIntent.ActionLoadData)
            }

            is ScreenStatus.Loading -> {
                ShowLoading()
            }

            is ScreenStatus.Success -> {
                ShowSuccess(uiState.content)
            }

            is ScreenStatus.Error -> {
                ShowError(uiState.screenState as ScreenStatus.Error)
            }

            else -> {
                ShowOther()
            }
        }
    }
}

@Composable
fun ShowOther() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Other")
    }
}

@Composable
fun ShowError(screenState: ScreenStatus.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${screenState.errorCode}, ${screenState.errorMessage}")
    }
}

@Composable
fun ShowSuccess(content: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = content)
    }
}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading")
    }
}