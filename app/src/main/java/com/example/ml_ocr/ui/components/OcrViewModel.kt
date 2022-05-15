package com.example.ml_ocr.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ml_ocr.common.dlog
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.nio.ByteBuffer

class OcrViewModel(private val ocrRepositoryImpl: OcrInterface) : ViewModel() {

    private val mutableOcrResult = MutableLiveData<String?>(null)
    val ocrResult: LiveData<String?> get() = mutableOcrResult

    fun processImage(context: Context, @DrawableRes resourceId: Int) {
        ocrRepositoryImpl.processImage(
            ocrRepositoryImpl.imageFromDrawableResource(
                context,
                resourceId
            ),
            onSuccess = { visionText ->
                dlog { "OCR RESULT: " }
                dlog { visionText.text }
                mutableOcrResult.postValue(visionText.text)
            },
            onError = { e ->
                dlog { "ERROR: $e" }
            }
        )
    }
}