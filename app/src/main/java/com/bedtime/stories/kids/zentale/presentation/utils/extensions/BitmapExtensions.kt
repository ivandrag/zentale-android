package com.bedtime.stories.kids.zentale.presentation.utils.extensions

import android.graphics.Bitmap
import android.graphics.Matrix
import java.io.ByteArrayOutputStream

fun Bitmap.rotateBitmap(rotationDegrees: Int): Bitmap {
    val matrix = Matrix().apply {
        postRotate(-rotationDegrees.toFloat())
        postScale(-1f, -1f)
    }

    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun Bitmap.bitmapToByteArray(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    compress(format, quality, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}

fun Bitmap?.notNull() = this != null
