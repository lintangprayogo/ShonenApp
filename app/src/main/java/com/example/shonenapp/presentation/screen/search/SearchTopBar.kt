package com.example.shonenapp.presentation.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.shonenapp.R
import com.example.shonenapp.ui.theme.topAppBarBackgroundColor
import com.example.shonenapp.ui.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .semantics {
                contentDescription = "SearchWidget"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "TextField"
                },
            value = text,
            onValueChange = onTextChanged,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.search_here),
                    color = Color.White
                )
            },
            textStyle = TextStyle(MaterialTheme.colors.topAppBarContentColor),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .semantics {
                            contentDescription = "SearchButton"
                        },
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.semantics {
                        contentDescription = "CloseButton"
                    },
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChanged.invoke("")
                        } else {
                            onCloseClicked.invoke()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked.invoke(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppBarContentColor
            )
        )
    }
}

@Composable
@Preview
private fun SearchTopBarPreview() {
    SearchTopBar(text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onCloseClicked = {})
}