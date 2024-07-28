package com.bedtime.stories.kids.zentale.data.local

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.OutputStream
import java.util.UUID

class SavePhotoLocalDataSourceImpl(
    private val context: Context
): SavePhotoLocalDataSource {

    override fun savePhotoToGallery(bitmap: Bitmap) {
        val resolver: ContentResolver = context.applicationContext.contentResolver

        val imageCollection: Uri = MediaStore.Images.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val nowTimestamp: Long = System.currentTimeMillis()
        val imageContentValues: ContentValues = ContentValues().apply {

            val imageName = UUID.randomUUID().toString() + ".jpg"
            put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

            put(MediaStore.MediaColumns.DATE_TAKEN, nowTimestamp)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/Zentale")
            put(MediaStore.MediaColumns.IS_PENDING, 1)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                put(MediaStore.Images.Media.DATE_TAKEN, nowTimestamp)
                put(MediaStore.Images.Media.DATE_ADDED, nowTimestamp)
                put(MediaStore.Images.Media.DATE_MODIFIED, nowTimestamp)
                put(MediaStore.Images.Media.AUTHOR, "Zentale")
                put(MediaStore.Images.Media.DESCRIPTION, "Toy photo")
            }
        }

        val imageMediaStoreUri: Uri? = resolver.insert(imageCollection, imageContentValues)

        // Write the image data to the new Uri.
        val result: Result<Unit> = imageMediaStoreUri?.let { uri ->
            kotlin.runCatching {
                resolver.openOutputStream(uri).use { outputStream: OutputStream? ->
                    checkNotNull(outputStream) { "Couldn't create file for gallery, MediaStore output stream is null" }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }

                imageContentValues.clear()
                imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                resolver.update(uri, imageContentValues, null, null)

                Result.success(Unit)
            }.getOrElse { exception: Throwable ->
                exception.message?.let(::println)
                resolver.delete(uri, null, null)
                Result.failure(exception)
            }
        } ?: run {
            Result.failure(Exception("Couldn't create file for gallery"))
        }
    }
}