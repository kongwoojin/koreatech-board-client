package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import koreatechboard.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val Typography.preferenceTitle: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp,
    )

val Typography.preferenceSummary: TextStyle
    get() = TextStyle(
        color = Color(0xFF808080),
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
    )

val Typography.articleTitle: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp,
    )

val Typography.articleSubText: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 4.sp,
        letterSpacing = 0.5.sp,
    )

val Typography.boardItemTitle: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )

val Typography.boardItemSubText: TextStyle
    get() = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 4.sp,
        letterSpacing = 0.5.sp,
    )


@OptIn(ExperimentalResourceApi::class)
@Composable
fun notoSansKR() = FontFamily(
    Font(Res.font.NotoSansKR_Regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.NotoSansKR_Bold, FontWeight.Bold, FontStyle.Normal),
    Font(Res.font.NotoSansKR_Thin, FontWeight.Thin, FontStyle.Normal),
    Font(Res.font.NotoSansKR_SemiBold, FontWeight.SemiBold, FontStyle.Normal),
    Font(Res.font.NotoSansKR_Medium, FontWeight.Medium, FontStyle.Normal),
    Font(Res.font.NotoSansKR_Light, FontWeight.Light, FontStyle.Normal),
    Font(Res.font.NotoSansKR_ExtraBold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(Res.font.NotoSansKR_Black, FontWeight.Black, FontStyle.Normal),
    Font(Res.font.NotoSansKR_ExtraLight, FontWeight.ExtraLight, FontStyle.Normal)
)