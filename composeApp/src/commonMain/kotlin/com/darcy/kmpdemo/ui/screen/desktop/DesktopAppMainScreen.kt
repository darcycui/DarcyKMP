package com.darcy.kmpdemo.ui.screen.desktop

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.darcy.kmpdemo.ui.screen.desktop.navigation.SidebarNavigationRailNavHost
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DesktopAppMainScreen() {
    val navController = rememberNavController()
    val startDestination = PhonePages.ChatList
    DesktopAppMainInnerPage(navController, startDestination)
}

@Composable
fun DesktopAppMainInnerPage(
    navController: NavHostController,
    startDestination: PhonePages,
    modifier: Modifier = Modifier
) {
    var selectedPageIndex by remember {
        mutableIntStateOf(PhonePages.ChatList.ordinal)
    }
    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.fillMaxHeight().width(120.dp),
        ) {
            PhonePages.entries.forEachIndexed { index, page ->
                NavigationRailItem(
                    selected = selectedPageIndex == index,
                    onClick = {
                        selectedPageIndex = index
                        navController.navigate(page.route) {
                            popUpTo(0) {
                                inclusive = true
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(painterResource(page.icon), contentDescription = null)
                    },
                    label = {
                        Text(stringResource(page.title))
                    }
                )
            }
        }
        SidebarNavigationRailNavHost(
            navController,
            startDestination,
            modifier = Modifier.weight(1f)
        )
    }
}