package com.appdev.feedback.ui.screens.postcomments.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appdev.feedback.domain.usecase.local.AddPostCommentCase
import com.appdev.feedback.domain.usecase.local.GetAllCommentsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCommentViewModel @Inject constructor(
    private val addPostCommentCase: AddPostCommentCase,
    private val getAllCommentsByIdUseCase: GetAllCommentsByIdUseCase
): ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }


}