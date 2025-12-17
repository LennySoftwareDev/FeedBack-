package com.appdev.feedback.data.api.repository

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.api.response.ApiPostUserServiceImpl
import com.appdev.feedback.utils.apiresult.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiPostRepository @Inject constructor(
    private val apiService: ApiPostUserServiceImpl
) {
    fun getAllUsersPublications(): Flow<ApiResult<List<UserPostDto>>> {
        return apiService.getAllPostUsers()
    }
}
