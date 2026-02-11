package com.darcy.kmpdemo.ui.screen.phone.friends.usecase

import com.darcy.kmpdemo.bean.http.FriendsResponse
import com.darcy.kmpdemo.repository.FriendsHttpRepository
import com.darcy.kmpdemo.ui.base.IUseCase

class FetchFriendsListUseCase(
    private val repository: FriendsHttpRepository = FriendsHttpRepository(),
) : IUseCase<FriendsResponse> {
    override suspend fun invoke(): Result<FriendsResponse> {
        val response = repository.getFriendsList()
        return if (response.items.isNotEmpty()) {
            Result.success(response)
        } else {
            Result.failure(Exception())
        }
    }
}