package com.darcy.kmpdemo.ui.base.impl.paging

import androidx.compose.foundation.text.input.TextFieldState
import com.darcy.kmpdemo.ui.base.IState

data class PagingState(
    val currentPageNumber: Int = DEFAULT_PAGE_NUMBER,
    val currentPageSize: PageSize = PageSize.DEFAULT,
    val jumpPageNumberTextState: TextFieldState = TextFieldState(""),
    val pageSizeMenuList: List<PageSize> = emptyList(),
    val showPageSizeMenu: Boolean = false,
    val totalCount: Int = 0,
    val selectedNewPageNumber: Int = 0,
    val totalPage: Int = calculateTotalPages(totalCount, currentPageSize.size),
) : IState {
    companion object {
        const val DEFAULT_PAGE_NUMBER = 1
        const val DEFAULT_PAGE_SIZE = 10

        fun calculateTotalPages(totalItems: Int, itemsPerPage: Int): Int {
            return if (itemsPerPage > 0) {
                (totalItems + itemsPerPage - 1) / itemsPerPage
            } else {
                1
            }
        }
    }
}
