package com.bedtime.stories.kids.zentale.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainViewModel(
    firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {
        firebaseAuth.addAuthStateListener { firebaseUser ->
            if (firebaseUser.currentUser != null) {
                _event.tryEmit(Event.LoggedIn)
                println("##Emit LoggedIn")
                return@addAuthStateListener
            }
            println("##Emit LoggedOut")
            _event.tryEmit(Event.LoggedOut)
        }
    }

    sealed interface Event {
        data object LoggedIn : Event
        data object LoggedOut : Event
    }
}
