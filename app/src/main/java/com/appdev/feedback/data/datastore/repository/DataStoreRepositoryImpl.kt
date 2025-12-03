package com.appdev.feedback.data.datastore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class DataStoreRepositoryImpl(
    private val context: Context,
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String {
        val prefKey = stringPreferencesKey(key)
        val prefs = dataStore.data.first()
        return prefs[prefKey] ?: ""
    }

    override suspend fun setDefaultCredentials() {
        putString("username", "android")
        putString("password", "developer")
    }
}