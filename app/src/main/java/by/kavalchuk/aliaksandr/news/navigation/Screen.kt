package by.kavalchuk.aliaksandr.news.navigation

import by.kavalchuk.aliaksandr.news.util.Constants.Screens.ABOUT_SCREEN
import by.kavalchuk.aliaksandr.news.util.Constants.Screens.DETAILS_SCREEN
import by.kavalchuk.aliaksandr.news.util.Constants.Screens.MAIN_SCREEN
import by.kavalchuk.aliaksandr.news.util.Constants.Screens.SETTING_SCREEN
import by.kavalchuk.aliaksandr.news.util.Constants.Screens.WELCOME_SCREEN

sealed class Screen(val route: String) {
    object Welcome: Screen(route = WELCOME_SCREEN)
    object Main: Screen(route = MAIN_SCREEN)
    object Details: Screen(route = DETAILS_SCREEN)
    object Setting: Screen(route = SETTING_SCREEN)
    object About: Screen(route = ABOUT_SCREEN)
}