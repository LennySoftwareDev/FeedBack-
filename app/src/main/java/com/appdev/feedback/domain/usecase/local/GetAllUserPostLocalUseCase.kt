package com.appdev.feedback.domain.usecase.local

import com.appdev.feedback.data.local.mapper.toUserPost
import com.appdev.feedback.data.local.repository.UserPostRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllUserPostLocalUseCase @Inject constructor(
    private val repository: UserPostRepository
) {
    operator fun invoke() = repository.getUserPosts()
        .map { userPostList ->
            userPostList.map { it.toUserPost() }
        }
}
