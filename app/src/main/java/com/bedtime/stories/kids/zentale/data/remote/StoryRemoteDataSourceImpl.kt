package com.bedtime.stories.kids.zentale.data.remote

import com.bedtime.stories.kids.zentale.domain.model.Story
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class StoryRemoteDataSourceImpl : StoryRemoteDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchDemoStory(id: String): Story? {
        val documentReference = firestore
            .collection("demo")
            .document("qFYXOvaHFlkZ3VjC8e0t")
            .collection("public")
            .document(id)

        return try {
            val snapshot = documentReference.get().await()
            if (snapshot.exists()) {
                snapshot.toObject(Story::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
