package by.kavalchuk.aliaksandr.news.util

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import by.kavalchuk.aliaksandr.news.util.Constants.Companion.TIME_FORMAT_WITH_TIMEZONE
import org.ocpsoft.prettytime.PrettyTime
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Constants {

    companion object {
//        const val NEWS_API_KEY = "8372d71c2abe4cb4a823c35e608a575a";
        const val TIME_FORMAT_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss"
    }

    object Keys {
        // Room DB
        const val NEWS_DATABASE = "news_database"
        const val NOTES_TABLE = "news_table"
        //
        const val ID = "publishedAt"
    }

    object Screens {
        const val WELCOME_SCREEN = "splash_screen"
        const val MAIN_SCREEN = "main_screen"
        const val DETAILS_SCREEN = "details_screen"
        const val SETTING_SCREEN = "setting_screen"
        const val ABOUT_SCREEN = "details_screen"
    }
}

fun getNewsCategories():List<String>{
    return listOf("business","entertainment","technology","health","science","sports"
        ,"general")
}

fun getRelativeDateTimeString(date: Date):CharSequence {
    var locale = Locale.getDefault()
    if (locale.language == "en" && Build.VERSION.SDK_INT >= 21)
    {
        locale = Locale.forLanguageTag("ru")
    }
    val prettyTime = PrettyTime(locale)
    return prettyTime.format(date)
}

fun String?.appendMore(): CharSequence = this?.let {
    val result = this.substringAfter("[").substringBefore(']')
    return this.replace("[$result]", " more...")
} ?: " more ..."

fun String?.appendNull(): CharSequence = this?.let {
    val result = this.substringAfter("[").substringBefore(']')
    return this.replace("[$result]", " ")
} ?: " "

fun getLocation(context: Context):String{
    val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.networkCountryIso
}


fun getDateIso(str:String):Date {
    if (TextUtils.isEmpty(str)) {
        return Date()
    }
    return try {
        SimpleDateFormat(TIME_FORMAT_WITH_TIMEZONE, Locale.ENGLISH).parse(str)!!
    }
    catch (e: ParseException) {
        e.printStackTrace()
        Timber.e("exception date"+e.localizedMessage)
        Date()
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