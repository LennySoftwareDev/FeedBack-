package com.appdev.feedback.ui.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev.feedback.data.datastore.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled

    private val _isLoginSuccess = MutableStateFlow(false)
    val isLogiSuccess: StateFlow<Boolean> = _isLoginSuccess

    init {
        login()
    }

    fun onUsernameChange(username: String) {
        _username.value = username
        validateInputs()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        validateInputs()
    }

    private fun validateInputs() {
        _isLoginEnabled.value = _username.value.isNotBlank() && _password.value.length >= 6
    }

    fun login() {
        viewModelScope.launch {
            val savedUser = dataStoreRepository.getString("username")
            val savedPass = dataStoreRepository.getString("password")

            _isLoginSuccess.value = _username.value == savedUser && _password.value == savedPass
        }
    }
}