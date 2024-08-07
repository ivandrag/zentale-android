package com.bedtime.stories.kids.zentale.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("subscription") val subscription: Subscription? = null
)

@Serializable
data class Subscription(
    @SerialName("textCredits") val textCredits: Long? = null
)
