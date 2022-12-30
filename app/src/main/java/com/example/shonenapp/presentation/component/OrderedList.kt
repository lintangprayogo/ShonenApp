package com.example.shonenapp.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.shonenapp.ui.theme.titleColor


@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier=Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            style = MaterialTheme.typography.subtitle1
                .copy(fontWeight = FontWeight.Medium, color = textColor)
        )

        items.forEachIndexed { index, data ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index + 1}. $data",
                style = MaterialTheme.typography.body1
                    .copy(fontWeight = FontWeight.Medium, color = textColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderedList() {
    OrderedList(
        title = "Skills",
        items = listOf("Chidori", "Rasengan"),
        textColor =MaterialTheme.colors.titleColor
    )
}

