package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.data.model.UserResponse
import com.bedtime.stories.kids.zentale.networking.FirestoreSerializer.toObjectWithSerializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class UserRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): UserRemoteDataSource {

    override suspend fun getUser() = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid ?: return@callbackFlow

        val documentReference = firestore
            .collection("users")
            .document(userId)

        val listenerRegistration = documentReference.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val userResponse: UserResponse? = snapshot.toObjectWithSerializer()
                trySend(userResponse).isSuccess
            } else {
                trySend(null).isSuccess
            }
        }

        awaitClose { listenerRegistration.remove() }
    }
}
