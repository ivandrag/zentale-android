package com.bedtime.stories.kids.zentale.domain

import com.bedtime.stories.kids.zentale.domain.model.UserBO
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    val user: StateFlow<UserBO>

    suspend fun getUser()
}
