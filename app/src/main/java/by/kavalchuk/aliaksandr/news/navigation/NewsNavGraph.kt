package by.kavalchuk.aliaksandr.news.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.kavalchuk.aliaksandr.news.screen.MainScreen
import by.kavalchuk.aliaksandr.news.screen.WelcomeScreen
import by.kavalchuk.aliaksandr.news.util.Constants.Companion.NEWS_API_KEY
import by.kavalchuk.aliaksandr.news.viewmodel.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NewsNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    val newsViewModel = hiltViewModel<NewsViewModel>()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
//            navController.popBackStack()

            newsViewModel.getLocalizedNews("ru")
            MainScreen(
                navController = navController,
                newsViewModel = newsViewModel
            )
        }
    }
}
