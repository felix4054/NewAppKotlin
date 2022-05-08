package by.kavalchuk.aliaksandr.news.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
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
    )
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "News",
                fontSize = 18.sp
            )
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen()
}
