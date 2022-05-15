package com.example.ml_ocr.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import com.example.ml_ocr.common.dlog
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.nio.ByteBuffer

class OcrRepositoryMock : OcrInterface {
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

}