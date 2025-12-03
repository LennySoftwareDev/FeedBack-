package com.appdev.feedback.data.api.request

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.utils.Constants
import retrofit2.http.GET

interface ApiPostUserService {
    @GET(Constants.GET_POSTS)
    suspend fun getAllPostUsers(): List<UserPostDto>
}