package com.example.urvarassignment_2.repositories

import com.example.urvarassignment_2.data.Post
import com.example.urvarassignment_2.roomDB.PostsDao

class PostRepository(private val postDatabaseDao: PostsDao) {

    val readAllData = postDatabaseDao.getAllPosts()

    suspend fun addPost(postItem: Post) {
        postDatabaseDao.insert(postItem)
    }

}