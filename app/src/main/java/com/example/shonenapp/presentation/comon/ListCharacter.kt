package com.example.shonenapp.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.borutoapp.ui.theme.CHARACTER_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.shonenapp.R
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.navigation.Screen
import com.example.shonenapp.ui.theme.topAppBarContentColor
import com.example.shonenapp.utils.Constant.BASE_URL


@Composable
fun ListCharacter(
    entries: LazyPagingItems<ShonenCharacterEntry>,
    navHostController: NavHostController,
) {
    val result = handlePaggingResult(entries = entries)
    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(
                SMALL_PADDING
            )
        ) {
            items(items = entries,
                key = { it.id }) { character ->
                character?.let {
                    CharacterItem(
                        character = character,
                        navHostController = navHostController
                    )
                }
            }
        }
    }

}

@Composable
fun handlePaggingResult(entries: LazyPagingItems<ShonenCharacterEntry>): Boolean {
    entries.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }


        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> false
            else -> true
        }
    }
}

@Composable
fun CharacterItem(character: ShonenCharacterEntry, navHostController: NavHostController) {

    Box(
        modifier = Modifier
            .height(CHARACTER_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.Details.passId(character.id))
            }, contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_URL${character.image}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f), color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomEnd = LARGE_PADDING, bottomStart = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = character.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Medium),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = character.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = character.rating
                    )
                    Text(
                        text = "${character.rating}",
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                    )
                }
            }

        }
    }
}

@Composable
@Preview
fun CharacterItemPreview() {
    CharacterItem(
        character = ShonenCharacterEntry(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            strength = 100,
            month = "",
            day = "",
            related = listOf(),
            skillSet = listOf(),
            elements = listOf()
        ),
        navHostController = rememberNavController()
    )
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HeroItemDarkPreview() {
    CharacterItem(
        character = ShonenCharacterEntry(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
            rating = 0.0,
            strength = 100,
            month = "",
            day = "",
            related = listOf(),
            skillSet = listOf(),
            elements = listOf()
        ),
        navHostController = rememberNavController()
    )
}