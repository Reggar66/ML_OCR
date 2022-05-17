package com.example.ml_ocr.ui.components

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ml_ocr.common.dlog

class OcrViewModel(private val ocrRepositoryImpl: OcrRepository) : ViewModel() {

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

    fun processImage(context: Context, uri: Uri) {
        ocrRepositoryImpl.imageFromPath(context, uri)?.let {
            ocrRepositoryImpl.processImage(
                it,
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
}