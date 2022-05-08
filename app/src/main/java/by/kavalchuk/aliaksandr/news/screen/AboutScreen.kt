package by.kavalchuk.aliaksandr.news.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AboutScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MAIN HOME",
            fontSize = MaterialTheme.typography.h4.fontSize
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
    AboutScreen()
}