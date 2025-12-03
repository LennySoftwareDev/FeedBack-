package com.appdev.feedback.utils.apiresult

sealed class ApiResult<out T>(val statusEnum: ApiStatusEnum, val response: T?, val throwable : Throwable) {
    data class Success<out T>(val info: T?): ApiResult<T>(statusEnum = ApiStatusEnum.SUCCESS, response = info, throwable = Throwable())
    data class Error<out T>(val info: T?, val exception: Throwable): ApiResult<T>(statusEnum = ApiStatusEnum.ERROR, response = info, throwable = exception)
}