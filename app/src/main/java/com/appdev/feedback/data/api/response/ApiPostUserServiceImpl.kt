package com.appdev.feedback.data.api.response

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.api.request.ApiPostUserService
import com.appdev.feedback.utils.apiresult.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiPostUserServiceImpl @Inject constructor(
    private val apiService: ApiPostUserService
) {
    fun getAllPostUsers(): Flow<ApiResult<List<UserPostDto>>> = flow {
        emit(ApiResult.Loading)
        try {
            emit(ApiResult.Success(apiService.getAllPostUsers()))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message))
        }
    }
}