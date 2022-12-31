package com.example.shonenapp.presentation.screen.detail

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
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
import com.example.borutoapp.ui.theme.*
import com.example.shonenapp.R
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.ui.theme.titleColor
import com.example.shonenapp.utils.Constant
import com.example.shonenapp.utils.Constant.ABOUT_TEXT_MAX_LINE
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    navHostController: NavHostController,
    shonenCharacterEntry: ShonenCharacterEntry,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkvibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = shonenCharacterEntry) {
        vibrant = colors["vibrant"]!!
        darkvibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkvibrant))
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )
    val currentFraction = scaffoldState.currenSheetFraction

    val radiusAnim = if (currentFraction == 1f)
        EXTRA_LARGE_PADDING
    else
        EXPANDED_RADIUS_LEVEL

    BottomSheetScaffold(
        sheetShape =
        RoundedCornerShape(
            topStart = radiusAnim, topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            BottomSheetContent(
                shonenCharacterEntry = shonenCharacterEntry,
                infoBoxIconColor = Color(parseColor(vibrant)),
                sheetBackgroundColor = Color(parseColor(darkvibrant)),
                contentColor = Color(parseColor(onDarkVibrant)),
            )
        }, content = {
            BackgorundContent(
                image = shonenCharacterEntry.image,
                name = shonenCharacterEntry.name,
                imageFraction = currentFraction,
                backgroundColor = Color(parseColor(darkvibrant))
            ) {
                navHostController.popBackStack()
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currenSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
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
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoxBox(
                icon = painterResource(id = R.drawable.ic_sword),
                iconColor = infoBoxIconColor,
                titleText = shonenCharacterEntry.strength.toString(),
                desText = stringResource(R.string.strength),
                textColor = contentColor
            )

            InfoxBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                titleText = shonenCharacterEntry.month,
                desText = stringResource(R.string.month),
                textColor = contentColor
            )

            InfoxBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                titleText = shonenCharacterEntry.day,
                desText = stringResource(R.string.birthday),
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

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                OrderedList(
                    title = stringResource(R.string.relative),
                    items = shonenCharacterEntry.related,
                    textColor = contentColor
                )
            }
            item {
                OrderedList(
                    title = stringResource(R.string.skils),
                    items = shonenCharacterEntry.skillSet,
                    textColor = contentColor
                )
            }
            item {
                OrderedList(
                    title = stringResource(R.string.elements),
                    items = shonenCharacterEntry.elements,
                    textColor = contentColor
                )
            }


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
                .fillMaxHeight(fraction = imageFraction + 0.5f)
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