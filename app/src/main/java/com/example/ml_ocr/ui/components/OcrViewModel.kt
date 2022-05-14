package com.example.ml_ocr.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.nio.ByteBuffer

class OcrViewModel : ViewModel() {
    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


    fun processImage(image: InputImage) {
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // TODO post result to LiveData. Use processTextBlock()
                // Task completed successfully
            }
            .addOnFailureListener { e ->
                // TODO post error
                // Task failed with an exception
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

    private fun imageFromBitmap(bitmap: Bitmap) {
        val rotationDegrees = 0
        val image = InputImage.fromBitmap(bitmap, 0)
    }

    private fun imageFromPath(context: Context, uri: Uri) {
        val image: InputImage
        try {
            image = InputImage.fromFilePath(context, uri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
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