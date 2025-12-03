package com.appdev.feedback.domain.usecase.api

import com.appdev.feedback.data.api.repository.ApiPostRepository
import javax.inject.Inject

class GetAllUserPostUseCase @Inject constructor(
    private val repository: ApiPostRepository
){
    suspend operator fun invoke() = repository.getAllUsersPublications()
}