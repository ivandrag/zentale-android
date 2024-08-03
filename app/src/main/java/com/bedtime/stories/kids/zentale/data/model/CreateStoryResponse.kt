package com.bedtime.stories.kids.zentale.data.model

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

@Serializable
data class CreateStoryResponse(
    @SerialName("data") val data: StoryResponse
)

@Serializable
data class StoryResponse(
    @SerialName("storyId") val storyId: String,
    @SerialName("storyImage") val storyImage: String,
    @SerialName("storyTitle") val storyTitle: String,
    @SerialName("storyContent") val storyContent: String,
    @SerialName("storyLanguage") val storyLanguage: String,
    @SerialName("storyAudioUrl") val storyAudioUrl: String? = null,
    @SerialName("status") val status: String? = null,
    @Serializable(with = FirestoreTimestampSerializer::class)
    @SerialName("createdAt") val createdAt: Instant
)

object FirestoreTimestampSerializer : KSerializer<Instant> {
    private val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm:ss a z")

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        val zonedDateTime = value.atZone(ZoneId.of("UTC+3"))
        val formattedString = formatter.format(zonedDateTime)
        encoder.encodeString(formattedString)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val dateString = decoder.decodeString()
        val localDateTime = LocalDateTime.parse(dateString, formatter)
        val zonedDateTime = localDateTime.atZone(ZoneId.of("UTC+3"))
        return zonedDateTime.toInstant()
    }
}
