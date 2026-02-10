package com.darcy.kmpdemo.ui.base

interface IUseCase<T> {
    suspend operator fun invoke(): Result<T>
}