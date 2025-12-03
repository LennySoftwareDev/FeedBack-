package com.appdev.feedback.data.local.repository

import com.appdev.feedback.data.api.modelresponse.UserPostDto
import com.appdev.feedback.data.local.database.dao.UserPostDao
import com.appdev.feedback.data.local.database.entities.UserPostEntity
import com.appdev.feedback.data.local.mapper.toUserPostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPostRepository @Inject constructor(
    private val userPostDao: UserPostDao
) {
    suspend fun insertUserPost(userPostMapper: UserPostDto) {
        userPostDao.insertUserPost(userPostMapper.toUserPostEntity())
    }

     fun getUserPosts(): Flow<List<UserPostEntity>> {
        return userPostDao.getUserPosts()
    }
}