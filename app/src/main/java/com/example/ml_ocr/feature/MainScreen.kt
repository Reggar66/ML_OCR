package com.example.ml_ocr.feature

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ml_ocr.ui.components.OcrViewModel
import com.example.ml_ocr.ui.components.PreviewContainer

@Composable
fun MainScreen(viewModel: OcrViewModel = viewModel()) {

}


@Preview
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    PreviewContainer {
        MainScreen()
    }
}



