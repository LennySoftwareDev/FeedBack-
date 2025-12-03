package com.appdev.feedback.data.api.response

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.api.request.ApiPostUserService
import com.appdev.feedback.utils.apiresult.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApiPostUserServiceImpl @Inject constructor(
    private val apiService: ApiPostUserService
) {
    suspend fun getAllPostUsers(): ApiResult<Flow<List<UserPostDto>>> {

        return withContext(Dispatchers.IO) {
            try {
                val result = flow {
                    emit(apiService.getAllPostUsers())
                }
                ApiResult.Success(result)
            } catch (e: HttpException) {
                ApiResult.Error(info = null, exception = e)
            } catch (e: IOException) {
                ApiResult.Error(info = null, exception = e)
            } catch (e: Exception) {
                ApiResult.Error(info = null, exception = e)
            }
        }
    }
}