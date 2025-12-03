package com.appdev.feedback.data.datastore.repository

interface DataStoreRepository {
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): String
    suspend fun setDefaultCredentials()
}