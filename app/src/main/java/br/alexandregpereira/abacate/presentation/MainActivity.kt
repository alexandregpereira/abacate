package br.alexandregpereira.abacate.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import br.alexandregpereira.abacate.domain.models.Post
import br.alexandregpereira.abacate.ui.component.AbacateCard
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Window(viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Window(viewModel: MainViewModel) = AbacateTheme {
    val posts: List<Post> by viewModel.posts.observeAsState(emptyList())
    LazyColumn {
        items(posts) { post ->
            AbacateCard(
                actionHeaderText = post.title,
                actionHeaderUrls = post.imageUrls,
                postText = post.message
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun DefaultPreview() {
    Window(MainViewModel())
}