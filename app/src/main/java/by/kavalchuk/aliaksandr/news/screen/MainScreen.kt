package by.kavalchuk.aliaksandr.news.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.news.R
import by.kavalchuk.aliaksandr.news.data.model.Article
import by.kavalchuk.aliaksandr.news.navigation.Screen
import by.kavalchuk.aliaksandr.news.util.*
import by.kavalchuk.aliaksandr.news.viewmodel.NewsViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import timber.log.Timber


@Composable
fun MainScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    val allNews = newsViewModel.recentNewsLiveData.observeAsState(listOf()).value

    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    items(allNews) { article ->
                        NewsItem(article = article, navController = navController)
                    }
                }
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

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NewsItem(article: Article, navController: NavController) {
    val articleTime = getRelativeDateTimeString(getDateIso(article.publishedAt))
    val articleDestination = article.description.appendNull().toString()
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
                navController.navigate(Screen.Details.route + "/${article.publishedAt}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
        ) {
            Box(
                modifier = Modifier.size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = article.urlToImage,
                    loading = {
                        Box(
                            modifier = Modifier.size(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

//            AsyncImage(
//                modifier = Modifier.size(80.dp),
//                model = article.urlToImage,
//                placeholder = painterResource(R.drawable.logo),
//                contentScale = ContentScale.Crop,
//                contentDescription = null
//            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = article.title!!,
                    maxLines = 2,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = articleDestination,
                    maxLines = 2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp, bottom = 4.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = articleTime.toString(),
                        fontSize = 12.sp,
//                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController(),
        newsViewModel = hiltViewModel()
    )
}
