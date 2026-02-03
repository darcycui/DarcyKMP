package com.darcy.kmpdemo.exception

open class BaseException (
    val errorCode: Int,
    val errorMessage: String
) : Exception("errorCode=$errorCode errorMessage=$errorMessage") {
}