package br.alexandregpereira.abacate.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@Composable
fun PostText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.Light,
        maxLines = 5,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Preview
@Composable
fun PostTextPreview() {
    AbacateTheme(darkTheme = true) {
        PostText(text = LoremIpsum(50).values.first())
    }
}