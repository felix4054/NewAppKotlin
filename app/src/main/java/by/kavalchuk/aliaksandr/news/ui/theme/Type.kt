package by.kavalchuk.aliaksandr.news.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import by.kavalchuk.aliaksandr.news.R


val Reemkufi = FontFamily(
    Font(R.font.reemkufi_regular, FontWeight.Normal),
    Font(R.font.reemkufi_bold, FontWeight.Bold),
    Font(R.font.reemkufi_semi_bold, FontWeight.SemiBold),
    Font(R.font.reemkufi_medium, FontWeight.Medium),
)

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_medium, FontWeight.Medium),
)

val Typography = Typography(
    defaultFontFamily = Poppins
)
