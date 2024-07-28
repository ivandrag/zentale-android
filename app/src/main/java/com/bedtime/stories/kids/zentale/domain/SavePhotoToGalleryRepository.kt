package com.bedtime.stories.kids.zentale.domain

import android.graphics.Bitmap

interface SavePhotoToGalleryRepository {
    fun savePhotoToGallery(bitmap: Bitmap)
}
