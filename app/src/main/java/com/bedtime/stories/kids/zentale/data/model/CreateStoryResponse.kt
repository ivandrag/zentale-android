package com.bedtime.stories.kids.zentale.data.model

import com.google.firebase.Timestamp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Serializable
data class CreateStoryResponse(
    @SerialName("data") val data: String
)

@Serializable
data class StoryResponse(
    @SerialName("storyId") val storyId: String? = null,
    @SerialName("storyImage") val storyImage: String? = null,
    @SerialName("storyTitle") val storyTitle: String? = null,
    @SerialName("storyContent") val storyContent: String? = null,
    @SerialName("storyLanguage") val storyLanguage: String? = null,
    @SerialName("storyAudioUrl") val storyAudioUrl: String? = null,
    @SerialName("status") val status: String? = null,
    @Serializable(with = FirestoreTimestampSerializer::class)
    @SerialName("createdAt") val createdAt: Date? = null
)

object FirestoreTimestampSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString()) // Serialize Date to a String
    }

    override fun deserialize(decoder: Decoder): Date {
        val dateString = decoder.decodeString()
        return Date(dateString)
    }
}
