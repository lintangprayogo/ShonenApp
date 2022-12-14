package com.example.shonenapp.presentation.comon

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.shonenapp.R
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.ui.theme.DarkGray
import com.example.shonenapp.ui.theme.LightGray
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    entries: LazyPagingItems<ShonenCharacterEntry>? = null,
) {
    var message by remember {
        mutableStateOf("Find Your Favorite Character")
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation)
            ContentAlpha.disabled else 0f,
        animationSpec = tween(1000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        error = error,
        entries = entries
    )
}

@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    entries: LazyPagingItems<ShonenCharacterEntry>? = null,
    error: LoadState.Error? = null
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            entries?.refresh()
            isRefreshing = false
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .alpha(alphaAnim)
                    .size(NETWORK_ERROR_ICON_HEIGHT),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )

            Text(
                modifier = Modifier
                    .alpha(alphaAnim)
                    .padding(top = SMALL_PADDING),
                text = message,
                style = MaterialTheme.typography.subtitle1
                    .copy(
                        color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                        fontWeight = FontWeight.Medium
                    ),
                textAlign = TextAlign.Center
            )
        }
    }
}

fun parseErrorMessage(state: LoadState.Error): String {
    return when (state.error) {
        is SocketTimeoutException -> {
            "Server Unvailable."
        }
        is ConnectException -> {
            "Internet Unvailable."
        }
        else -> state.error.message ?: "Unknown Error."
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyScreen() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewEmptyDarkScreen() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}
