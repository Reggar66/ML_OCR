package com.example.ml_ocr.feature

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.nio.ByteBuffer

class OcrRepositoryImpl : OcrRepository {
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


    override fun processImage(
        image: InputImage,
        onSuccess: (visionText: Text) -> Unit,
        onError: (e: Exception) -> Unit
    ) {
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                onSuccess(visionText)
            }
            .addOnFailureListener { e ->
                onError(e)
            }
    }

    private fun processTextBlock(result: Text) {
        val resultText = result.text
        for (block in result.textBlocks) {
            val blockText = block.text
            val blockCornerPoints = block.cornerPoints
            val blockFrame = block.boundingBox
            for (line in block.lines) {
                val lineText = line.text
                val lineCornerPoints = line.cornerPoints
                val lineFrame = line.boundingBox
                for (element in line.elements) {
                    val elementText = element.text
                    val elementCornerPoints = element.cornerPoints
                    val elementFrame = element.boundingBox
                }
            }
        }
    }

    override fun imageFromDrawableResource(
        context: Context,
        @DrawableRes drawableRes: Int,
        rotationDegrees: Int
    ): InputImage {
        return imageFromBitmap(
            context.resources.getDrawable(drawableRes, null).toBitmap(),
            rotationDegrees
        )
    }

    private fun imageFromBitmap(bitmap: Bitmap, rotationDegrees: Int = 0): InputImage {
        return InputImage.fromBitmap(bitmap, rotationDegrees)
    }

    override fun imageFromPath(context: Context, uri: Uri): InputImage? {
        var image: InputImage? = null
        try {
            image = InputImage.fromFilePath(context, uri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    private fun imageFromBuffer(byteBuffer: ByteBuffer, rotationDegrees: Int) {
        val image = InputImage.fromByteBuffer(
            byteBuffer,
            /* image width */ 480,
            /* image height */ 360,
            rotationDegrees,
            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
        )
    }

    private fun imageFromArray(byteArray: ByteArray, rotationDegrees: Int) {
        val image = InputImage.fromByteArray(
            byteArray,
            /* image width */ 480,
            /* image height */ 360,
            rotationDegrees,
            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
        )
    }
}