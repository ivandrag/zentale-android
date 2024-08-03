package com.bedtime.stories.kids.zentale.domain

interface UploadPhotoRepository {

    suspend fun uploadAndGetPublicUrl(
        imageBytes: ByteArray
    ): String
}
