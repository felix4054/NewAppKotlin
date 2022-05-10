package by.kavalchuk.aliaksandr.news.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.news.R
import by.kavalchuk.aliaksandr.news.data.model.Article
import by.kavalchuk.aliaksandr.news.viewmodel.NewsViewModel
import by.kavalchuk.aliaksandr.news.ui.theme.NewsTheme
import by.kavalchuk.aliaksandr.news.util.appendMore
import by.kavalchuk.aliaksandr.news.util.appendNull
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun DetailScreen(navController: NavHostController, newsViewModel: NewsViewModel, itemId: String) {
    val allNews = newsViewModel.recentNewsLiveData.observeAsState(listOf()).value
    val article = allNews.firstOrNull { it.publishedAt == itemId } ?: Article()

    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                LazyColumn {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxWidth(),
                                model = article.urlToImage,
//                        placeholder = painterResource(R.drawable.logo),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
//                    Image(
//                        painter = rememberAsyncImagePainter(article.urlToImage),
//                        contentDescription = null,
//                        modifier = Modifier.size(360.dp)
//                    )
                            Text(
                                text = article.title!!,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = article.description.appendNull().toString(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light
                            )
//
//                    Button(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp)
//                            .padding(top = 16.dp),
//                        onClick = {
//                            navController.popBackStack()
//                        }) {
//                        Text("Nav Back")
//                    }
                        }
                    }
                }
            }
        }
    )
}

//@Composable
//fun TopBar() {
//    TopAppBar(
//        title = {
//            Text(
//                text = "News",
//                fontSize = 18.sp
//            )
//        },
//        backgroundColor = Color.White,
//        contentColor = Color.Black
//    )
//}


@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    NewsTheme {
        DetailScreen(
            navController = rememberNavController(),
            newsViewModel = hiltViewModel(),
            itemId = "1",
        )
    }
}