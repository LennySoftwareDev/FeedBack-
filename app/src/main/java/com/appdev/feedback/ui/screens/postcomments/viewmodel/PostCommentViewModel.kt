package com.appdev.feedback.ui.screens.postcomments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev.feedback.data.local.mapper.toPostComment
import com.appdev.feedback.data.local.models.PostCommentDto
import com.appdev.feedback.domain.usecase.local.AddPostCommentCase
import com.appdev.feedback.domain.usecase.local.GetAllCommentsByIdUseCase
import com.appdev.feedback.ui.models.PostComment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PostCommentViewModel @Inject constructor(
    private val addPostCommentCase: AddPostCommentCase,
    private val getAllCommentsByIdUseCase: GetAllCommentsByIdUseCase
) : ViewModel() {


    private val _postId = MutableStateFlow(0)
    val postId = _postId.asStateFlow()

    private val _postComments = MutableStateFlow<List<PostComment>>(emptyList())
    val postComments = _postComments.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onCommentCreated(postComment: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addPostCommentCase.invoke(
                PostCommentDto(
                    postId = _postId.value,
                    body = postComment
                )
            )
        }
    }

    init {
        _postId
            .filter { it > 0 }
            .flatMapLatest { postId ->
                getAllCommentsByIdUseCase.invoke(postId)
                    .map { entityList ->
                        entityList.map { it.toPostComment() }
                    }
            }
            .onEach { mappedList ->
                _postComments.value = mappedList
            }
            .launchIn(viewModelScope)


    }

    fun getPostCommentsById(postId: Int) {
        _postId.value = postId
    }
}


