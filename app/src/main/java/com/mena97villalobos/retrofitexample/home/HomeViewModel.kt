package com.mena97villalobos.retrofitexample.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mena97villalobos.retrofitexample.network.Post
import com.mena97villalobos.retrofitexample.network.TestingAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts


    private val _postById = MutableLiveData<Post>()
    val postById: LiveData<Post>
        get() = _postById


    private val _postInserted = MutableLiveData<Post>()
    val postInserted: LiveData<Post>
        get() = _postInserted


    fun getAllPosts() =
        viewModelScope.launch { getAllPostsAux() }


    private suspend fun getAllPostsAux() =
        withContext(Dispatchers.IO){
            _posts.postValue(TestingAPI.retrofitService.getAllPosts())
        }


    fun getPostById(postId: Int) =
        viewModelScope.launch { getPostByIdAux(postId) }


    private suspend fun getPostByIdAux(postId: Int) =
        withContext(Dispatchers.IO) {
            _postById.postValue(TestingAPI.retrofitService.getPostById(postId))
        }


    fun insertPost(post:Post) =
        viewModelScope.launch { insertPostAux(post) }


    private suspend fun insertPostAux(post: Post) =
        withContext(Dispatchers.IO) {
            _postInserted.postValue(TestingAPI.retrofitService.createPost(post))
        }

}