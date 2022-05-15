package com.example.ml_ocr.common

import com.example.ml_ocr.common.KoinModules.repositories
import com.example.ml_ocr.common.KoinModules.viewModels
import com.example.ml_ocr.ui.components.OcrRepositoryImpl
import com.example.ml_ocr.ui.components.OcrViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allModules = listOf(repositories, viewModels)

private object KoinModules {
    val repositories = module {
        single { OcrRepositoryImpl() }
    }

    val viewModels = module {
        viewModel { OcrViewModel(get<OcrRepositoryImpl>()) }
    }
}