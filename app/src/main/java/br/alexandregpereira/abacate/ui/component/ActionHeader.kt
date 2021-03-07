package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme
import br.alexandregpereira.abacate.ui.theme.GrayScale100
import br.alexandregpereira.abacate.ui.theme.White
import dev.chrisbanes.accompanist.coil.CoilImage

const val IMAGE_URL_DEFAULT = "https://picsum.photos/300/300"
private const val MAX_IMAGES = 6

@ExperimentalAnimationApi
@Composable
fun ActionHeader(
    text: String,
    urls: List<String> = emptyList()
) {
    var opened by remember { mutableStateOf(false) }
    val rowModifier = Modifier.clickable(enabled = true) {
        opened = opened.not()
    }
    Row(
        modifier = rowModifier
            .padding(all = 16.dp)
            .defaultMinSize(minHeight = 40.dp)
            .fillMaxWidth()
    ) {
        OvalImages(
            urls = urls,
            opened = opened
        )

        val textPaddingStart = if (urls.isEmpty()) 0.dp else 16.dp
        AnimatedVisibility(
            visible = !opened,
            enter = fadeIn(),
            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { -it }),
            modifier = Modifier
                .padding(start = textPaddingStart)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = text,
                maxLines = 2
            )
        }
    }
}

@Composable
fun OvalImages(
    urls: List<String> = emptyList(),
    opened: Boolean = false
) {
    val transition = updateTransition(targetState = opened)
    val secondPaddingIncrease by transition.animateDp { stateOpened ->
        if (stateOpened) {
            48.dp
        } else {
            if (urls.size == 2) 32.dp else 24.dp
        }
    }

    val thirdAndMorePaddingIncrease by transition.animateDp(
        transitionSpec = {
            if (targetState) {
                spring(
                    visibilityThreshold = Dp.VisibilityThreshold
                )
            } else {
                spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    visibilityThreshold = Dp.VisibilityThreshold
                )
            }
        }
    ) { stateOpened ->
        if (stateOpened) {
            48.dp
        } else {
            4.dp
        }
    }

    Box {
        val maxImages = if (urls.size > MAX_IMAGES) MAX_IMAGES else urls.size
        var startPadding = 0.dp
        urls.subList(0, maxImages).forEachIndexed { index, url ->
            OvalImage(
                url,
                modifier = Modifier.padding(start = startPadding)
            )
            startPadding += if (index == 0) {
                secondPaddingIncrease
            } else {
                thirdAndMorePaddingIncrease
            }
        }
    }
}

@Composable
fun OvalImage(
    url: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = url,
        contentDescription = "My content description",
        fadeIn = true,
        modifier = modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(color = GrayScale100)
            .border(width = 4.dp, color = White, shape = CircleShape)
    )
}

@ExperimentalAnimationApi
@Preview
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
@Preview
@Composable
fun ActionHeaderPreview6() {
    AbacateTheme {
        ActionHeader(
            text = "Anything asdsa d\nasdasdasddasd asdas\nasdasdasdasd",
            urls = (0..10).map { IMAGE_URL_DEFAULT }
        )
    }
}