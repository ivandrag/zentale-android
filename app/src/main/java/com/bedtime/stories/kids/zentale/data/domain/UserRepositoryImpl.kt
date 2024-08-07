package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.data.local.UserLocalDataSource
import com.bedtime.stories.kids.zentale.data.model.UserResponse
import com.bedtime.stories.kids.zentale.data.remote.UserRemoteDataSource
import com.bedtime.stories.kids.zentale.domain.UserRepository
import com.bedtime.stories.kids.zentale.domain.model.UserBO

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {
    override val user = userLocalDataSource.user

    override suspend fun getUser() {
        userRemoteDataSource.getUser().collect {
            userLocalDataSource.saveUser(it.toUserBO())
        }
    }

    private fun UserResponse?.toUserBO() = UserBO(
        textCredits = this?.subscription?.textCredits.toString()
    )
}
