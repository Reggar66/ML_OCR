package com.example.ml_ocr.feature

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ml_ocr.R
import com.example.ml_ocr.ui.components.OcrRepositoryMock
import com.example.ml_ocr.ui.components.OcrViewModel
import com.example.ml_ocr.ui.components.PreviewContainer
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: OcrViewModel = getViewModel()) {
    val resultText: String? by viewModel.ocrResult.observeAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ocr_hp_quote),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.processImage(context, R.drawable.ocr_hp_quote)
        }) {
            Text(text = "Do OCR")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result:")
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = resultText ?: "None."
        )
    }
}


@Preview
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    PreviewContainer {
        MainScreen(OcrViewModel(OcrRepositoryMock()))
    }
}
