package com.example.shonenapp.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shonenapp.R
import com.example.shonenapp.navigation.Screen
import com.example.shonenapp.ui.theme.Purple500
import com.example.shonenapp.ui.theme.Purple700

@Composable
fun ShonenSplashScreen(
    navHostController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val cordinate = remember { Animatable(100f) }

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 = true) {
        cordinate.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navHostController.popBackStack()
        if (onBoardingCompleted) {
            navHostController.navigate(Screen.Home.route)
        } else {
            navHostController.navigate(Screen.Welcome.route)
        }
    }

    SplashScreenContent(cordinate.value)
}


@Composable
fun SplashScreenContent(cordinate:Float) {
    if(isSystemInDarkTheme()){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(top = cordinate.dp)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.ic_fist),
                contentDescription = "ShonenIcon")
        }
    }else{
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Purple700, Purple500))),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(top = cordinate.dp)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.ic_fist),
                contentDescription = "ShonenIcon")
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreenContent(0f)
}