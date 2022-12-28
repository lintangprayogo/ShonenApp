package com.example.shonenapp.presentation.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.shonenapp.R
import com.example.shonenapp.ui.theme.topAppBarBackgroundColor
import com.example.shonenapp.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(title = {

        Text(text = "Explore", color = MaterialTheme.colors.topAppBarContentColor)
    },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.icon_search)
                )
            }
        })
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar {}
}

