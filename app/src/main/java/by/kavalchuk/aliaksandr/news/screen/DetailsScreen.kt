package by.kavalchuk.aliaksandr.news.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import by.kavalchuk.aliaksandr.news.viewmodel.MainViewModel
import by.kavalchuk.aliaksandr.news.ui.theme.NewsTheme

@Composable
fun DetailScreen(mainViewModel: MainViewModel, itemId: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "DETAILS SCREEN")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    NewsTheme {
        DetailScreen(
            mainViewModel = hiltViewModel(),
            itemId = "1"
        )
    }
}