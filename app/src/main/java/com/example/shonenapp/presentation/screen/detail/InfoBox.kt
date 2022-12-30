package com.example.shonenapp.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.shonenapp.R
import com.example.shonenapp.ui.theme.titleColor

@Composable
fun InfoxBox(
    icon: Painter,
    iconColor: Color,
    titleText: String,
    desText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .size(INFO_ICON_SIZE)
                .padding(end = SMALL_PADDING),
            painter = icon,
            contentDescription = titleText,
            tint = iconColor
        )
        Column {
            Text(
                text = titleText, style = MaterialTheme.typography.h6.copy(
                    color = textColor,
                    fontWeight = FontWeight.Black
                )
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = desText,
                style = MaterialTheme.typography.overline.copy(
                    color = textColor,
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewInfoBox() {
    InfoxBox(
        icon = painterResource(id = R.drawable.ic_sword),
        iconColor = MaterialTheme.colors.primary,
        titleText = "Power",
        desText = "99",
        textColor = MaterialTheme.colors.titleColor
    )
}