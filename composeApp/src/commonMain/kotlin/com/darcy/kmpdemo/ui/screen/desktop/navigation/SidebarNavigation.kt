package com.darcy.kmpdemo.ui.screen.desktop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.darcy.kmpdemo.ui.screen.phone.chatlist.PhoneChatListScreen
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhonePages
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darcy.kmpdemo.log.logD
import com.darcy.kmpdemo.ui.screen.phone.dynamic.PhoneDynamicScreen
import com.darcy.kmpdemo.ui.screen.phone.friends.PhoneFriendsScreen
import com.darcy.kmpdemo.ui.screen.phone.mine.PhoneMineScreen
import com.darcy.kmpdemo.ui.screen.phone.navigation.PhoneRoute

object SidebarNavigation {
}

@Composable
fun SidebarNavigationRailNavHost(
    navHostController: NavHostController,
    startPages: PhonePages,
    modifier: Modifier = Modifier
) {
    NavHost(
        navHostController,
        startDestination = startPages.route
    ) {
        composable<PhoneRoute.ChatList> {
            PhoneChatListScreen()
        }
        composable<PhoneRoute.Friends> {
            PhoneFriendsScreen()
        }
        composable<PhoneRoute.Dynamic> {
            PhoneDynamicScreen()
        }
        composable<PhoneRoute.Mine> {
            PhoneMineScreen()
        }
    }

}

