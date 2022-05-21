package com.example.ml_ocr.feature

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text

interface OcrRepository {
    fun imageFromDrawableResource(
        context: Context,
        @DrawableRes drawableRes: Int,
        rotationDegrees: Int = 0
    ): InputImage

    fun processImage(
        image: InputImage,
        onSuccess: (visionText: Text) -> Unit,
        onError: (e: Exception) -> Unit
    )

    fun imageFromPath(context: Context, uri: Uri): InputImage?

}