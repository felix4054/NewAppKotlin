package by.kavalchuk.aliaksandr.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.news.navigation.NewsNavGraph
import by.kavalchuk.aliaksandr.news.ui.theme.NewsTheme
import by.kavalchuk.aliaksandr.news.viewmodel.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            NewsTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()

                NewsNavGraph(
                    navController = navController,
                    startDestination = screen
                )
            }
        }
    }
}

