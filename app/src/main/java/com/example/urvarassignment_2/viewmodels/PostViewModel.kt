package com.example.urvarassignment_2

import android.app.Application
import androidx.lifecycle.*
import com.example.urvarassignment_2.data.Post
import com.example.urvarassignment_2.repositories.PostRepository
import com.example.urvarassignment_2.roomDB.PostDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: List<Post>
    private val repository: PostRepository

    init {
        val postDao = PostDatabase.getInstance(application).todoDao()
        repository = PostRepository(postDao)
        readAllData = repository.readAllData
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }
}


class PostViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}