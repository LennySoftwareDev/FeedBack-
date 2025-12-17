package com.appdev.feedback.di

import android.content.Context
import androidx.room.Room
import com.appdev.feedback.data.local.database.dao.UserPostDao
import com.appdev.feedback.data.local.database.dao.UserPostDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataBaseModule {

    @Provides
    @Singleton
    fun provideUserPublicationDataBase(@ApplicationContext context: Context): UserPostDataBase {
        return Room.databaseBuilder(context, UserPostDataBase::class.java, "user_post_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserPostDao(userPostDataBase: UserPostDataBase): UserPostDao {
        return userPostDataBase.userPostDao()
    }
}