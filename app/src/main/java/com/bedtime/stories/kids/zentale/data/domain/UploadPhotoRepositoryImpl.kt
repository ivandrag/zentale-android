package com.bedtime.stories.kids.zentale.data.domain

import com.bedtime.stories.kids.zentale.domain.UploadPhotoRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import java.util.UUID

class UploadPhotoRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val storage: FirebaseStorage
): UploadPhotoRepository {

    override suspend fun uploadAndGetPublicUrl(
        imageBytes: ByteArray,
        timeoutMillis: Long
    ): String {
        return try {
            withTimeout(timeoutMillis) {
                val userId = firebaseAuth.currentUser!!.uid
                val uniqueID = UUID.randomUUID().toString()
                val storageRef = storage.reference.child(userId).child("$uniqueID.jpg")

                val uploadTaskSnapshot = storageRef.putBytes(imageBytes).await()

                if (uploadTaskSnapshot.bytesTransferred == imageBytes.size.toLong()) {
                    storageRef.downloadUrl.await().toString()
                } else {
                    throw Exception("Upload was not successful")
                }
            }
        } catch (e: Exception) {
            throw Exception("Upload was not successful: ${e.message}", e)
        }
    }
}
