package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme
import br.alexandregpereira.abacate.ui.theme.GrayScale100
import br.alexandregpereira.abacate.ui.theme.White
import dev.chrisbanes.accompanist.coil.CoilImage

const val IMAGE_URL_DEFAULT = ""
private const val MAX_IMAGES = 5

@ExperimentalAnimationApi
@Composable
fun ActionHeader(
    text: String,
    modifier: Modifier = Modifier,
    urls: List<String> = emptyList()
) {
    var opened by remember { mutableStateOf(false) }
    val rowModifier = Modifier
        .then(modifier)
        .height(height = 40.dp)
        .fillMaxWidth()
        .semantics { testTag = "ActionHeader" }
        .run {
            if (opened) {
                horizontalScroll(rememberScrollState())
            } else {
                this
            }
        }
    Row(
        modifier = rowModifier
    ) {

        if (urls.isNotEmpty()) {
            val onTap: (Offset) -> Unit = {
                opened = opened.not()
            }
            CircleImages(
                urls = urls,
                opened = opened,
                onTap = onTap,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        val textPaddingStart = if (urls.isEmpty()) 16.dp else 0.dp
        TextVisibility(
            text = text,
            visible = !opened,
            modifier = Modifier
                .padding(start = textPaddingStart, end = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun TextVisibility(
    text: String,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = { it * 3 }),
        exit = slideOutHorizontally(targetOffsetX = { it * 3 }),
        modifier = modifier
    ) {
        Text(
            text = text,
            maxLines = 2
        )
    }
}

@ExperimentalAnimationApi
@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun TextVisibilityPreview() {
    AbacateTheme(darkTheme = true) {
        var visible by remember { mutableStateOf(true) }
        TextVisibility("teste", visible)
        Button(onClick = { visible = !visible }, modifier = Modifier.padding(top = 24.dp)) {
            Text(text = "Click")
        }
    }
}

@Composable
fun CircleImages(
    urls: List<String>,
    opened: Boolean,
    onTap: (Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingTransition = updateTransition(targetState = opened)
    val secondPaddingIncrease by paddingTransition.animatePaddingDp { stateOpened ->
        if (stateOpened) {
            48.dp
        } else {
            if (urls.size == 2) 32.dp else 24.dp
        }
    }

    val thirdAndMorePaddingIncrease by paddingTransition.animatePaddingDp { stateOpened ->
        if (stateOpened) 48.dp else 4.dp
    }

    var pressed by remember { mutableStateOf(false) }
    val boxModifier = Modifier
        .then(modifier)
        .pressedGesture(
            enabled = urls.size > 2,
            onTap = onTap,
            onPressed = { pressed = it }
        )
    Box(boxModifier) {
        var startPadding = 16.dp
        urls.forEachIndexed { index, url ->
            CircleImage(
                url,
                pressed = pressed,
                modifier = Modifier.padding(start = startPadding, end = 16.dp)
            )
            startPadding += when {
                index == 0 -> secondPaddingIncrease
                index in MAX_IMAGES..urls.size && opened.not() -> 0.dp
                else -> thirdAndMorePaddingIncrease
            }
        }
    }
}

@Composable
fun CircleImage(
    url: String,
    modifier: Modifier = Modifier,
    pressed: Boolean = false
) {
    val scale = animatePressed(pressed = pressed)
    CoilImage(
        data = url,
        contentDescription = "My content description",
        fadeIn = true,
        modifier = modifier
            .size((40 * scale).dp)
            .clip(shape = CircleShape)
            .background(color = GrayScale100)
            .border(width = 4.dp, color = White, shape = CircleShape)
            .semantics { testTag = "OvalImage" }
    )
}

@Composable
private fun Transition<Boolean>.animatePaddingDp(
    targetValueByState: @Composable (Boolean) -> Dp
) = animateDp(
    transitionSpec = {
        if (targetState) {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = Dp.VisibilityThreshold
            )
        } else {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                visibilityThreshold = Dp.VisibilityThreshold
            )
        }
    },
    targetValueByState = targetValueByState
)

@ExperimentalAnimationApi
@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun ActionHeaderPreview0() {
    AbacateTheme {
        ActionHeader(
            text = "Anything"
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun ActionHeaderPreview1() {
    AbacateTheme {
        ActionHeader(
            text = "Anything",
            urls = listOf(IMAGE_URL_DEFAULT)
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun ActionHeaderPreview2() {
    AbacateTheme {
        ActionHeader(
            text = "Anything asdsa d\nasdasdasddasd asdas\nasdasdasdasd",
            urls = listOf(IMAGE_URL_DEFAULT, IMAGE_URL_DEFAULT)
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun ActionHeaderPreview3() {
    AbacateTheme {
        ActionHeader(
            text = "Anything asdsa d\nasdasdasddasd asdas\nasdasdasdasd",
            urls = listOf(IMAGE_URL_DEFAULT, IMAGE_URL_DEFAULT, IMAGE_URL_DEFAULT)
        )
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun ActionHeaderPreview6() {
    AbacateTheme {
        ActionHeader(
            text = "Anything asdsa d\nasdasdasddasd asdas\nasdasdasdasd",
            urls = (0..10).map { IMAGE_URL_DEFAULT }
        )
    }
}