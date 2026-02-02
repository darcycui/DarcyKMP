package com.darcy.kmpdemo.ui.screen.phone.mine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmpdarcydemo.composeapp.generated.resources.Res
import kmpdarcydemo.composeapp.generated.resources.page_mine
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhoneMineScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(stringResource(Res.string.page_mine))
        }
    }
}