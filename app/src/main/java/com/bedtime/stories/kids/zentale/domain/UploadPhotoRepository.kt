package com.bedtime.stories.kids.zentale.domain

interface UploadPhotoRepository {

    suspend fun uploadAndGetPublicUrl(
        imageBytes: ByteArray,
        timeoutMillis: Long = 10000
    ): String
}
