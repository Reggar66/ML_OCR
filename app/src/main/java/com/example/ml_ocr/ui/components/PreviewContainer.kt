package com.example.ml_ocr.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ml_ocr.ui.theme.ML_OCRTheme

@Composable
fun PreviewContainer(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    ML_OCRTheme {
        Surface {
            Column(
                modifier = modifier,
                content = content,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment
            )
        }
    }
}