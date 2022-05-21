package com.example.ml_ocr.feature

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ml_ocr.R
import com.example.ml_ocr.ui.components.PreviewContainer
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(viewModel: OcrViewModel = getViewModel()) {
    val resultText: String? by viewModel.ocrResult.observeAsState()
    val context = LocalContext.current
    var currentImage by remember {
        mutableStateOf(items.first())
    }

    val bitMap by viewModel.bitMap.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /* Image chooser */
        ListOfImages {
            currentImage = it
        }
        Spacer(modifier = Modifier.height(30.dp))

        /* Do ocr*/
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(shape = CircleShape, onClick = {
                viewModel.clearBitmap()
                viewModel.processImage(context, currentImage)
            }) {
                Text(text = "OCR")
            }

            Button(shape = CircleShape, onClick = {
                viewModel.processFromUrlRequest(
                    context,
                    "https://media.geeksforgeeks.org/wp-content/uploads/20190528023339/Testproc1.jpg"
                )
            }) {
                Text(text = "URL OCR")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        /* Current Image */
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = currentImage),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(16.dp))

        /* Result */
        Result(result = resultText)
        Spacer(modifier = Modifier.height(16.dp))

        bitMap?.asImageBitmap()?.let {
            Text(text = "Image from URL")
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = it,
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

private val items = listOf(
    R.drawable.ocr_hp_quote,
    R.drawable.ocr_wiki_1,
    R.drawable.ocr_wiki_2,
    R.drawable.ocr_wiki_3,
    R.drawable.testproc1
)

@Composable
private fun ListOfImages(onItemClick: (drawableRes: Int) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items) { item ->
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clickable { onItemClick(item) },
                painter = painterResource(id = item),
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun Result(result: String?) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = 200.dp)
            .fillMaxWidth(),
        backgroundColor = Color.DarkGray
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Result:")
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = result ?: "None."
            )
        }
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
