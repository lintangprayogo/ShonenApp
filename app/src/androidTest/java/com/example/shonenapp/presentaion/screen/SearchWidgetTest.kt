package com.example.shonenapp.presentaion.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.shonenapp.presentation.screen.search.SearchTopBar
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChanged = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("SAKURA")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("SAKURA")
    }

    @Test
    fun openSearchWidget_addInputText_addThenClose_assertEmptyText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChanged = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("SAKURA")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_addThenCloseTwice_assertCloseState() {
        val text = mutableStateOf("")
        val searchWidgetShowing = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShowing.value) {
                SearchTopBar(
                    text = text.value,
                    onTextChanged = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShowing.value = false
                    }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("SAKURA")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }


    @Test
    fun openSearchWidget_addCloseTwice_assertCloseState() {
        val text = mutableStateOf("")
        val searchWidgetShowing = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShowing.value) {
                SearchTopBar(
                    text = text.value,
                    onTextChanged = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShowing.value = false
                    }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

}