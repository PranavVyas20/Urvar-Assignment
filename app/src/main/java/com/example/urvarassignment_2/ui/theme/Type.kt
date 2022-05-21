package com.example.urvarassignment_2.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.urvarassignment_2.R

// Set of Material typography styles to start with

private val RoundVarela = FontFamily(
    Font(R.font.nunito_semibold, FontWeight.W700)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),h1 = TextStyle(
        fontFamily = RoundVarela,
        fontWeight = FontWeight.W800,
        color = Color(0xFF3A3A3A),
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = RoundVarela,
        fontWeight = FontWeight.W300,
        color = Color(0xFF3A3A3A),
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = RoundVarela,
        fontWeight = FontWeight.W300,
        color = Color(0xFF3A3A3A),
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = RoundVarela,
        fontWeight = FontWeight.W300,
        color = Color(0xFF3A3A3A),
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = RoundVarela,
        fontWeight = FontWeight.W700,
        color = Color(0xFF3A3A3A),
        fontSize = 18.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)