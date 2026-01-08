package com.darcy.kmpdemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.darcy.kmpdemo.navigation.Pages
import kmpdarcydemo.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import dev.icerock.moko.resources.compose.stringResource as mokoStringResource

@Composable
fun ShowHome(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (String) -> Unit = {}
) {
    // darcyRefactor: 可观察的状态列表
//    val pagesStateList = remember { mutableStateListOf<Pages>() }
    val pagesStateList =  mutableStateListOf<Pages>()
    Pages.entries.forEachIndexed { index, it ->
        if (index > 1) {
            pagesStateList.add(it)
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        // 垂直间距
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(pagesStateList.size) { index ->
            HomeItem(onNextButtonClicked = onNextButtonClicked, page = pagesStateList[index])
        }
    }
}

@Composable
fun HomeItem(
    onNextButtonClicked: (String) -> Unit = {}, page: Pages
) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = {
        onNextButtonClicked(page.name)
    }) {
//        Text(text = mokoStringResource(page.title))
        Text(text = stringResource(page.title),
//            style = MaterialTheme.typography.titleSmall,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}