package com.darcy.kmpdemo.ui.base

interface IUseCase<T> {
    suspend operator fun invoke(params: Map<String, String>): Result<T>
}