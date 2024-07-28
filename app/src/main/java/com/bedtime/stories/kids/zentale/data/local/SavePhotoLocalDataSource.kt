package com.bedtime.stories.kids.zentale.data.local

import android.graphics.Bitmap

interface SavePhotoLocalDataSource {

    fun savePhotoToGallery(bitmap: Bitmap)
}
