package com.example.shonenapp.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.*
import com.example.shonenapp.ui.theme.ShimmerDarkGray
import com.example.shonenapp.ui.theme.ShimmerLightGray
import com.example.shonenapp.ui.theme.ShimmerMediumGray


@Composable
fun ShimmerAnimatedItem() {
    val transaction = rememberInfiniteTransition()
    val alphaAnimation = transaction.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation  = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnimation.value)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(CHARACTER_ITEM_HEIGHT),
        color = if (isSystemInDarkTheme()) {
            Color.Black
        } else {
            ShimmerLightGray
        },
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {

            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth(0.5f)
                    .height(NAME_PLACEHOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) {
                    ShimmerDarkGray
                } else {
                    ShimmerMediumGray
                },
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {
            }

            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            repeat(3){
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .fillMaxWidth()
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) {
                        ShimmerDarkGray
                    } else {
                        ShimmerMediumGray
                    },
                    shape = RoundedCornerShape(size = EXTRA_LARGE_PADDING)
                ) {
                }
                Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
            }

            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
                ) {
                repeat(5){
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(RATING_PLACEHOLDER_HEIGHT),
                        color = if (isSystemInDarkTheme()) {
                            ShimmerDarkGray
                        } else {
                            ShimmerMediumGray
                        },
                        shape = RoundedCornerShape(size = EXTRA_LARGE_PADDING)
                    ) {
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun ShimmerItemPreview(){
 ShimmerAnimatedItem()
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ShimmerItemDarkPreview(){
    ShimmerAnimatedItem()
}

