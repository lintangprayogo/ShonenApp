package com.example.shonenapp.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.shonenapp.R
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.ui.theme.titleColor
import com.example.shonenapp.utils.Constant
import com.example.shonenapp.utils.Constant.ABOUT_TEXT_MAX_LINE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    navHostController: NavHostController,
    shonenCharacterEntry: ShonenCharacterEntry
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContent(shonenCharacterEntry = shonenCharacterEntry)
        }, content = {
            BackgorundContent(
                image = shonenCharacterEntry.image,
                name = shonenCharacterEntry.name
            ) {
                navHostController.popBackStack()
            }
        })
}

@Composable
fun BottomSheetContent(
    shonenCharacterEntry: ShonenCharacterEntry,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_japan_flower),
                contentDescription = stringResource(R.string.app_logo),
                tint = contentColor
            )

            Text(
                modifier = Modifier.weight(8f),
                text = shonenCharacterEntry.name,
                style = MaterialTheme.typography.h4
                    .copy(color = contentColor, fontWeight = FontWeight.Bold)
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoxBox(
                icon = painterResource(id = R.drawable.ic_sword),
                iconColor = infoBoxIconColor,
                titleText = stringResource(R.string.strength),
                desText = shonenCharacterEntry.strength.toString(),
                textColor = contentColor
            )

            InfoxBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                titleText = stringResource(R.string.month),
                desText = shonenCharacterEntry.month,
                textColor = contentColor
            )

            InfoxBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                titleText = stringResource(R.string.birthday),
                desText = shonenCharacterEntry.day,
                textColor = contentColor
            )
        }

        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.subtitle1
                .copy(color = contentColor, fontWeight = FontWeight.Bold)
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            text = shonenCharacterEntry.about,
            style = MaterialTheme.typography.body1
                .copy(color = contentColor),
            maxLines = ABOUT_TEXT_MAX_LINE
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.relative),
                items = shonenCharacterEntry.related,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.skils),
                items = shonenCharacterEntry.skillSet,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.elements),
                items = shonenCharacterEntry.elements,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BackgorundContent(
    image: String,
    name: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(Alignment.TopStart),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("${Constant.BASE_URL}${image}")
                .error(R.drawable.ic_placeholder)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = name,
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onCloseClicked) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White
                )
            }
        }

    }

}

@Preview
@Composable
private fun PreviewBottomSheetContent() {
    BottomSheetContent(
        shonenCharacterEntry = ShonenCharacterEntry(
            id = 2,
            name = "Naruto",
            image = "/images/naruto.jpg",
            about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
            rating = 5.0,
            strength = 98,
            month = "Oct",
            day = "10th",
            related = listOf(
                "Minato",
                "Kushina",
                "Boruto",
                "Himawari",
                "Hinata"
            ),
            skillSet = listOf(
                "Rasengan",
                "Rasen-Shuriken",
                "Shadow Clone",
                "Senin Mode"
            ),
            elements = listOf(
                "Wind",
                "Earth",
                "Lava",
                "Fire"
            )
        )
    )
}