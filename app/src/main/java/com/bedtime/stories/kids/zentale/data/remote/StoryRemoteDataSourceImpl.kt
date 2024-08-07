package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.data.model.PaginatedResult
import com.bedtime.stories.kids.zentale.data.model.StoryResponse
import com.bedtime.stories.kids.zentale.domain.model.Story
import com.bedtime.stories.kids.zentale.networking.FirestoreSerializer.toObjectWithSerializer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class StoryRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : StoryRemoteDataSource {

    override suspend fun fetchDemoStory(id: String) = callbackFlow {
        val documentReference = firestore
            .collection("demo")
            .document("qFYXOvaHFlkZ3VjC8e0t")
            .collection("public")
            .document(id)

        val listenerRegistration = documentReference.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val story = snapshot.toObject(Story::class.java)
                trySend(story)
            } else {
                trySend(null).isSuccess
            }
        }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun fetchStory(id: String) = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid
        if (userId == null) {
            close(Exception("User not authenticated"))
            return@callbackFlow
        }

        val documentReference = firestore
            .collection("stories")
            .document(userId)
            .collection("private")
            .document(id)

        val listenerRegistration = documentReference.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val storyResponse: StoryResponse? = snapshot.toObjectWithSerializer()
                trySend(storyResponse).isSuccess
            } else {
                trySend(null).isSuccess
            }
        }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun fetchStories() = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid
        if (userId == null) {
            close(Exception("User not authenticated"))
            return@callbackFlow
        }

        val collectionReference = firestore
            .collection("stories")
            .document(userId)
            .collection("private")

        val listenerRegistration = collectionReference
            .limit(PAGE_SIZE.toLong())
            .addSnapshotListener { querySnapshot, exception ->
                if (exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }

                if (querySnapshot != null) {
                    if (querySnapshot.documents.isNotEmpty()) {
                        trySend(
                            PaginatedResult(
                                stories = querySnapshot.documents.map {
                                    val storyResponse: StoryResponse? = it.toObjectWithSerializer()
                                    storyResponse!!
                                },
                                lastVisibleStory = querySnapshot.documents.lastOrNull()
                            )
                        )
                    } else {
                        close()
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun fetchMoreStories(lastVisibleStory: DocumentSnapshot?) = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid
        if (userId == null) {
            close(Exception("User not authenticated"))
            return@callbackFlow
        }

        if (lastVisibleStory == null) {
            close()
            return@callbackFlow
        }

        val collectionReference = firestore
            .collection("stories")
            .document(userId)
            .collection("private")

        val listenerRegistration = collectionReference
            .startAfter(lastVisibleStory)
            .limit(PAGE_SIZE.toLong())
            .addSnapshotListener { querySnapshot, exception ->
                if (exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }

                if (querySnapshot != null) {
                    if (querySnapshot.documents.isNotEmpty()) {
                        trySend(
                            PaginatedResult(
                                stories = querySnapshot.documents.map {
                                    val storyResponse: StoryResponse? = it.toObjectWithSerializer()
                                    storyResponse!!
                                },
                                lastVisibleStory = querySnapshot.documents.lastOrNull()
                            )
                        )
                    } else {
                        close()
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
