package br.alexandregpereira.abacate.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.alexandregpereira.abacate.domain.models.Post

class MainViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        _posts.value = (0..100).map { Post() }
    }
}