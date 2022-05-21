package com.example.ml_ocr.feature

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text

class OcrRepositoryMock : OcrRepository {
    override fun imageFromDrawableResource(
        context: Context,
        drawableRes: Int,
        rotationDegrees: Int
    ): InputImage {
        TODO("Not yet implemented")
    }

    override fun processImage(
        image: InputImage,
        onSuccess: (visionText: Text) -> Unit,
        onError: (e: Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun imageFromPath(context: Context, uri: Uri): InputImage? {
        TODO("Not yet implemented")
    }

}