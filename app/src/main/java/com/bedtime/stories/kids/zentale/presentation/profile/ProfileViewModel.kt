package com.bedtime.stories.kids.zentale.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bedtime.stories.kids.zentale.domain.UserRepository
import com.bedtime.stories.kids.zentale.domain.model.UserBO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.user.collect {
                _state.value = generateState(it)
            }
        }
        viewModelScope.launch {
            userRepository.getUser()
        }
    }

    private fun generateState(user: UserBO?): ProfileState {
        return ProfileState(
            textCredits = user?.textCredits ?: ""
        )
    }

    data class ProfileState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val textCredits: String = ""
    )
}
