package com.example.ml_ocr.common

import android.util.Log

fun dlog(tag: String = "INFO", msg: () -> Any?) = Log.d(tag, msg().toString())
