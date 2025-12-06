package com.appdev.feedback.domain.usecase.local

import com.appdev.feedback.data.local.repository.PostCommentRepository
import javax.inject.Inject

class GetAllCommentsByIdUseCase @Inject constructor(
    private val repository: PostCommentRepository
) {
    operator fun invoke(postId : Int) = repository.getPostComments(postId)
}