package com.appdev.feedback.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextComponent(
    text: String,
    fontSize : TextUnit = 12.sp,
    style: TextStyle = TextStyle.Default,
    maxLines: Int = Int.MAX_VALUE,
    color: Color,
    overflow: TextOverflow = TextOverflow.Ellipsis
){
    Text(
        text = text,
        fontSize = fontSize,
        style = style,
        maxLines = maxLines,
        color = color,
        overflow = overflow
    )
}

@Preview
@Composable
fun TextComponentPreview(){
    TextComponent(text = "Hola", color = Color.Black)
}