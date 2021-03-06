package br.alexandregpereira.abacate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme
import br.alexandregpereira.abacate.ui.theme.GrayScale100
import br.alexandregpereira.abacate.ui.theme.White
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ActionHeader(
    text: String,
    onImageClick: (() -> Unit)? = null
) {
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Box {
            OvalImage(onClick = onImageClick)
            OvalImage(modifier = Modifier.padding(start = 32.dp), onClick = onImageClick)
        }
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun OvalImage(modifier: Modifier = Modifier, onClick: (() -> Unit)? = null) {
    CoilImage(
        data = "https://picsum.photos/300/300",
        contentDescription = "My content description",
        fadeIn = true,
        modifier = modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(color = GrayScale100)
            .border(width = 4.dp, color = White, shape = CircleShape)
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    )
}

@Preview
@Composable
fun ActionHeaderPreview1() {
    AbacateTheme {
        ActionHeader(text = "Anything", onImageClick = {})
    }
}

@Preview
@Composable
fun ActionHeaderPreview2() {
    AbacateTheme {
        ActionHeader(text = "Anything asdsa d\nasdasdasddasd asdas\nasdasdasdasd")
    }
}