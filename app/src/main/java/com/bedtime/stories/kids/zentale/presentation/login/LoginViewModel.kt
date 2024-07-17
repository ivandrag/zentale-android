package com.bedtime.stories.kids.zentale.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun login() {
        // Handle login logic
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}