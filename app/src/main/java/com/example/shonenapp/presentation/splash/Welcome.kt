package com.example.shonenapp.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.PAGING_INDICATOR_SPACING
import com.example.borutoapp.ui.theme.PAGING_INDICATOR_WIDTH
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.shonenapp.R
import com.example.shonenapp.domain.model.OnBoardingPage
import com.example.shonenapp.ui.theme.*
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top) {
            WelcomeContent(pages[this.currentPage])
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.actviceIndacator,
            inactiveColor = MaterialTheme.colors.inActiveIndacator,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )

        FinishButton(modifier = Modifier.weight(1f),
            pagerState,
            onClick = {})
    }

}

@Composable
fun WelcomeContent(onBoardingPage: OnBoardingPage) {
    Column(Modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colors.welcomeScreenBackgroundColor
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.titleColor,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colors.descriptionColor,
            textAlign = TextAlign.Center
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun FinishButton(modifier: Modifier, pagerState: PagerState, onClick: () -> Unit) {
    Row(modifier
        .padding(horizontal = EXTRA_LARGE_PADDING)
        .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = pagerState.currentPage == pagerState.pageCount - 1) {
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonColor,
                    contentColor = Color.White
                )
                ) {
                Text(text = "Finish")
            }
        }

    }

}

@Preview
@Composable
fun WelcomeFirstPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        WelcomeContent(OnBoardingPage.First)
    }
}

@Preview
@Composable
fun WelcomeSecondPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        WelcomeContent(OnBoardingPage.Second)
    }
}

@Preview
@Composable
fun WelcomeThirdPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        WelcomeContent(OnBoardingPage.Third)
    }
}

