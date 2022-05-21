package com.example.urvarassignment_2.roomDB

import android.content.Context
import androidx.room.*
import com.example.urvarassignment_2.data.Post
import com.example.urvarassignment_2.data.PostTypeConverter

@Database(entities = [Post::class], version = 1, exportSchema = false)
@TypeConverters(PostTypeConverter::class)
abstract class PostDatabase : RoomDatabase() {
    abstract fun todoDao(): PostsDao

    companion object {

        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "todo_list_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}