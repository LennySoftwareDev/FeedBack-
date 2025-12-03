package com.appdev.feedback.ui.screens.socialmediaposts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev.feedback.domain.usecase.api.GetAllUserPostUseCase
import com.appdev.feedback.domain.usecase.local.AddUserPostUseCase
import com.appdev.feedback.domain.usecase.local.GetAllUserPostLocalUseCase
import com.appdev.feedback.ui.models.UserPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSocialMediaPosts @Inject constructor(
    private val getSocialMediaPostsUseCase: GetAllUserPostUseCase,
    private val addSocialMediaPostUseCase: AddUserPostUseCase,
    private val getAllUserPostLocalUseCase: GetAllUserPostLocalUseCase
) : ViewModel() {

    private val _isLoadingUsers = MutableStateFlow(false)
    val isLoadingUsers = _isLoadingUsers.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _posts = getAllUserPostLocalUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val posts: StateFlow<List<UserPost>> = _posts

    init {
        getSocialMediaPosts()
    }

    fun getSocialMediaPosts() {
        _isLoadingUsers.value = true
        viewModelScope.launch {
            if (_posts.value.isEmpty()) {
                val dataUserFromApi = getSocialMediaPostsUseCase()
                dataUserFromApi.response?.collect {
                    it.forEach { userPostDto ->
                        addSocialMediaPostUseCase(userPostDto)
                    }
                }
                _isLoadingUsers.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun filterDatabaseListUserPost(
        originalList: List<UserPost>,
        searchPostTitle: String
    ): List<UserPost> {
        val lowerCaseSearchText = searchPostTitle.trim().lowercase()

        if (lowerCaseSearchText.isEmpty()) {
            return originalList
        }

        return originalList.filter { item ->
            val isNameMatch = item.title.lowercase().contains(lowerCaseSearchText)

            val isIdMatch = try {
                item.id.toString().contains(lowerCaseSearchText)
            } catch (_: NumberFormatException) {
                false
            }

            isNameMatch || isIdMatch
        }
    }
}
