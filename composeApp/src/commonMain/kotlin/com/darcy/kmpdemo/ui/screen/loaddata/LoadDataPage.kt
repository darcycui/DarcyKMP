package com.darcy.kmpdemo.ui.screen.loaddata

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darcy.kmpdemo.ui.base.impl.fetch.FetchIntent
import com.darcy.kmpdemo.ui.base.impl.paging.PagingIntent
import com.darcy.kmpdemo.ui.base.impl.screenstatus.ScreenState
import com.darcy.kmpdemo.ui.base.impl.tips.TipsIntent
import com.darcy.kmpdemo.ui.components.structure.TipsDialog
import com.darcy.kmpdemo.ui.screen.loaddata.state.LoadDataState
import com.darcy.kmpdemo.ui.screen.loaddata.viewmodel.LoadDataViewModel

@Composable
fun ShowLoadDataPage() {
    val viewmodel: LoadDataViewModel = viewModel(factory = LoadDataViewModel.Factory)
    LaunchedEffect(Unit) {
        viewmodel.dispatch(FetchIntent.ActionLoadData)
    }
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
            is ScreenState.Initial -> {
                ShowLoading()
            }

            is ScreenState.Loading -> {
                ShowLoading()
            }

            is ScreenState.Success -> {
                ShowSuccess(uiState, viewModel)
            }

            is ScreenState.Error -> {
                ShowError(uiState.screenState as ScreenState.Error)
            }

            else -> {
                ShowOther()
            }
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
fun ShowOther() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Other")
    }
}

@Composable
fun ShowError(screenState: ScreenState.Error) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${screenState.errorCode}, ${screenState.errorMessage}")
    }
}

@Composable
fun ShowSuccess(uiState: LoadDataState, viewModel: LoadDataViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = uiState.content, modifier = Modifier.align(Alignment.Center))
        Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
            Button(modifier = Modifier.weight(1f), onClick = {
                val pageNumber =
                    if (uiState.pagingState.currentPageNumber > 1) (uiState.pagingState.currentPageNumber - 1) else 1
                viewModel.dispatch(PagingIntent.ActionLoadNewPage(pageNumber))
            }) {
                Text(text = "上一页")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.dispatch(FetchIntent.ActionLoadData)
            }) {
                Text(text = "刷新")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                val pageNumber = uiState.pagingState.currentPageNumber + 1
                viewModel.dispatch(PagingIntent.ActionLoadNewPage(pageNumber))
            }) {
                Text(text = "下一页")
            }
        }
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