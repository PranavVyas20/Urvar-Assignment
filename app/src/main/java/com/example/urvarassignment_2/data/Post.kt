package com.example.urvarassignment_2.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.urvarassignment_2.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "posts_table")
data class Post(

    val postDescription:String? = null,
    val postImage:Int? = null,
    val noOfLikes:Int = 0,
    val comments: List<String> = listOf(),
    val postTime:String?,
    val tag:String?,
    val postedBy:String = "Pranav Vyas",
    val postedByUserImg:Int = R.drawable.userpic,
    @PrimaryKey(autoGenerate = true)
    var postId:Int = 0
) : Parcelable


class PostTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType =object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun frmArrayList(list: List<String?>): String {
        return Gson().toJson(list)
    }
}
