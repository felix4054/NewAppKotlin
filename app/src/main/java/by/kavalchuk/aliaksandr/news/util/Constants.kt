package by.kavalchuk.aliaksandr.news.util

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat


class Constants {

    object Keys {
        // Room DB
        const val NEWS_DATABASE = "news_database"
        const val NOTES_TABLE = "news_table"
        //
    }

    object Screens {
        const val WELCOME_SCREEN = "splash_screen"
        const val MAIN_SCREEN = "main_screen"
        const val DETAILS_SCREEN = "details_screen"
        const val SETTING_SCREEN = "setting_screen"
        const val ABOUT_SCREEN = "details_screen"
    }
}

@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)}
    )
}