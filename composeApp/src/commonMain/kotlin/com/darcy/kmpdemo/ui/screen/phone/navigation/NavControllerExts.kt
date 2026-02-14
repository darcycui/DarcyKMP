package com.darcy.kmpdemo.ui.screen.phone.navigation

import androidx.navigation.NavHostController


/**
 * 返回上一页
 */
fun NavHostController.customGoBack() {
    this.popBackStack()
}

/**
 * 跳转到指定页面
 */
fun NavHostController.customNavigate(
    route: PhoneRoute,
    clearStack: Boolean = true,
    includeRoot: Boolean = true
) {
    this.navigate(route) {
        if (clearStack) {
            popUpTo(0) {
                inclusive = includeRoot
            }
        }
    }
}

/**
 * 清空回退栈
 */
fun NavHostController.customClearStack(route: PhoneRoute) {
    this.clearBackStack<PhoneRoute>()
}