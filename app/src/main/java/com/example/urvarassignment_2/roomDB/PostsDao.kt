package com.example.urvarassignment_2.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.urvarassignment_2.data.Post

@Dao
interface PostsDao {

    @Query(value = "SELECT * from posts_table")
     fun getAllPosts():List<Post>

    @Insert
    suspend fun insert(item: Post)
}