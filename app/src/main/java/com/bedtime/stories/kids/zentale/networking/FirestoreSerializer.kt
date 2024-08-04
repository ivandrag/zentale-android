package com.bedtime.stories.kids.zentale.networking

import com.google.firebase.firestore.DocumentSnapshot

object FirestoreSerializer {
    inline fun <reified T> DocumentSnapshot.toObjectWithSerializer(): T? {
        return try {
            this.toObject(T::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
