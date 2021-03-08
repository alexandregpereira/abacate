package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@ExperimentalAnimationApi
@Composable
fun AbacateCard(
    actionHeaderText: String,
    actionHeaderUrls: List<String>,
    postText: String,
    modifier: Modifier = Modifier,
) {
    PressRippleCard(modifier) {
        Column {
            ActionHeader(
                text = actionHeaderText,
                urls = actionHeaderUrls,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            PostText(
                text = postText,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun PressRippleCard(
    modifier: Modifier = Modifier,
    shape: Shape  = MaterialTheme.shapes.medium,
    color: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(color),
    border: BorderStroke? = null,
    elevation: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale = animatePressed(pressed = pressed, pressedScale = 0.96f)
    val elevationPx = with(LocalDensity.current) { elevation.toPx() }
    val elevationOverlay = LocalElevationOverlay.current
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    val backgroundColor = if (color == MaterialTheme.colors.surface && elevationOverlay != null) {
        elevationOverlay.apply(color, absoluteElevation)
    } else {
        color
    }
    val cardModifier = modifier
        .padding(all = 8.dp)
        .graphicsLayer(
            scaleX = scale,
            scaleY = scale,
            shadowElevation = elevationPx,
            shape = shape
        )
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .background(color = backgroundColor, shape = shape)
        .clip(shape)
        .pressedGesture(
            rippleEffectEnabled = true,
            onTap = {},
            onPressed = { pressed = it }
        )

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(cardModifier) {
            content()
        }
    }
}

@Preview
@ExperimentalAnimationApi
@Composable
fun AbacateCardPreview() {
    AbacateTheme {
        AbacateCard(
            actionHeaderText = "Anything asdsa asdasd asd asd asdasd as das dsa sa das",
            actionHeaderUrls = (0..10).map { IMAGE_URL_DEFAULT },
            postText = "Aasdas asd asd asdasdasdas asddasdasd"
        )
    }
}