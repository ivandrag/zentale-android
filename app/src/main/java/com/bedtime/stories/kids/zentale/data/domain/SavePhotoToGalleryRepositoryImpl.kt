package com.bedtime.stories.kids.zentale.data.domain

import android.graphics.Bitmap
import com.bedtime.stories.kids.zentale.data.local.SavePhotoLocalDataSource
import com.bedtime.stories.kids.zentale.domain.SavePhotoToGalleryRepository

class SavePhotoToGalleryRepositoryImpl(
    private val savePhotoLocalDataSource: SavePhotoLocalDataSource
): SavePhotoToGalleryRepository {

    override fun savePhotoToGallery(bitmap: Bitmap) {
        savePhotoLocalDataSource.savePhotoToGallery(bitmap)
    }
}
