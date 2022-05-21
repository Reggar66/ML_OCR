package com.example.ml_ocr.feature

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coil.imageLoader
import coil.request.ImageRequest
import com.example.ml_ocr.common.dlog
import com.google.mlkit.vision.common.InputImage

class OcrViewModel(private val ocrRepositoryImpl: OcrRepository) : ViewModel() {

    private val mutableOcrResult = MutableLiveData<String?>(null)
    val ocrResult: LiveData<String?> get() = mutableOcrResult

    private val mBitMap = MutableLiveData<Bitmap?>(null)
    val bitMap: LiveData<Bitmap?> get() = mBitMap

    fun clearBitmap() = mBitMap.postValue(null)

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

    fun processFromUrlRequest(context: Context, url: String) {
        val request = ImageRequest.Builder(context)
            .data(url)
            .listener(
                onStart = { dlog { "urlRequest: Start" } },
                onCancel = { dlog { "urlRequest: Cancel" } },
                onError = { request, result -> dlog { "urlRequest: Error" } },
                onSuccess = { request, result ->
                    dlog { "urlRequest: Success" }
                    dlog { result.memoryCacheKey?.key }
                    mBitMap.postValue(result.drawable.toBitmap())

                    // TODO this doesn't work but should. I guess Coil makes some shenanigans
                    //  with bitmap that ML Kit can not process correctly.
                    //  We get "Failed to lock bitmap pixels"
                    processImage(context, result.drawable.toBitmap())
                }
            )
            .build()

        context.imageLoader.enqueue(request)
    }

    private fun processImage(context: Context, bitmap: Bitmap) {
        ocrRepositoryImpl.processImage(
            InputImage.fromBitmap(bitmap, 0),
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