package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.domain.model.UserBO
import kotlinx.coroutines.flow.StateFlow

interface UserLocalDataSource {

    val user: StateFlow<UserBO>
    suspend fun saveUser(user: UserBO)
}
