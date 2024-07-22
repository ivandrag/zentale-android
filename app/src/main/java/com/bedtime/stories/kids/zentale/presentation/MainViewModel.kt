package com.bedtime.stories.kids.zentale.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->

    }

    init {
        firebaseAuth.addAuthStateListener { auth ->
            if (auth.currentUser != null) {
                viewModelScope.launch {
                    _event.emit(Event.LoggedIn)
                }
            } else {
                viewModelScope.launch {
                    _event.emit(Event.LoggedOut)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    sealed interface Event {
        data object LoggedIn : Event
        data object LoggedOut : Event
    }
}
