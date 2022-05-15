package com.example.ml_ocr.ui.components

import android.content.Context
import androidx.annotation.DrawableRes
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text

interface OcrInterface {
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

}