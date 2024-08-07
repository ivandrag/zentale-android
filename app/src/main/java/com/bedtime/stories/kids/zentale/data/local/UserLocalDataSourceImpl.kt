package com.bedtime.stories.kids.zentale.data.local

import com.bedtime.stories.kids.zentale.domain.model.UserBO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserLocalDataSourceImpl : UserLocalDataSource {
    private val _user = MutableStateFlow(UserBO())
    override val user = _user.asStateFlow()

    override suspend fun saveUser(user: UserBO) {
        _user.emit(user)
    }
}
