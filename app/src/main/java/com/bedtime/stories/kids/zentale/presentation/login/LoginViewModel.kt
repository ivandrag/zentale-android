package com.bedtime.stories.kids.zentale.presentation.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun onSuccessfulLogin(tokenId: String) {
        firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(tokenId, null))
            .addOnCompleteListener {
                if (it.isSuccessful) {

                } else {

                }
            }
    }

    fun onErrorLogin() {

    }

    data class LoginState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}