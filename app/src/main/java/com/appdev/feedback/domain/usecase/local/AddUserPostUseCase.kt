package com.appdev.feedback.domain.usecase.local

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.local.mapper.toUserPostEntity
import com.appdev.feedback.data.local.repository.UserPostRepository
import javax.inject.Inject

class AddUserPostUseCase @Inject constructor(
    private val repository: UserPostRepository
) {
    suspend operator fun invoke(userPostMapper: UserPostDto) {
        repository.insertUserPost(userPostMapper)
    }
}