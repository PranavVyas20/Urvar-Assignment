package com.example.urvarassignment_2

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.urvarassignment_2.data.Post
import com.example.urvarassignment_2.data.User
import com.example.urvarassignment_2.destinations.SecondScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyComposables {

    companion object{

        fun delayRender(){
            CoroutineScope(Dispatchers.IO).launch {
                delay(10000L)
            }
        }

        val tabItemList: List<String> = listOf("charcha", "bazaar", "profile")

        val testPost1: Post = Post(
            "What is urvar ?",
            null,
            12,
            listOf(
                "Hii this is first comment",
                "This is second comment from me !",
                "Another test comment here"),
            "2 hours ago",
            "Question")

        val testPost2: Post = Post(
            "Post with an image",
            R.drawable.test_img_2,
            8,
            listOf(
                "Hii this is first comment",
                "This is second comment from me !",
                "Another test comment here"),
            "2 hours ago",
            "Question"
        )

        val testPost3: Post = Post(
            "Another test post",
            null,
            21,
            listOf(
                "Hii this is first comment",
                "This is second comment from me !",
                "Another test comment here"),
            "2 hours ago",
            "Question"
        )

        val testPost4: Post = Post(
            "Just landed on urvar",
            R.drawable.img3,
            6,
            listOf(
                "Hii this is first comment",
                "This is second comment from me !",
                "Another test comment here"),
            "2 hours ago",
            "Question"
        )

        val posList: List<Post> = listOf(testPost1, testPost2, testPost3, testPost4)
        @Composable
        fun PostLikeView(noOfLikes: Int) {
            var isChecked by remember {
                mutableStateOf(false)
            }
            Row(
                modifier = Modifier.padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                IconToggleButton(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    modifier = Modifier.width(28.dp)
                ) {
                    val tint by animateColorAsState(if (isChecked) Color(0xFF386BDD) else Color(0xFFB0BEC5))
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                        tint = tint)
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$noOfLikes likes",
                    style = MaterialTheme.typography.h5
                )
            }
        }

        @Composable
        fun PostCommentsView(post:Post,Postnavigator: DestinationsNavigator){
            Row(
                modifier = Modifier
                    .clickable {
                        // pass the above comment list to the second screen
                        post.postDescription?.let { Log.d("tagggggg", it) }
                        Postnavigator.navigate(
                            SecondScreenDestination(post)
                        ) },

                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Chat,
                    contentDescription = "comment btn icon",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text( text = "${post.comments.size} comments",
                    style = MaterialTheme.typography.h5)
            }
        }

        @Composable
        fun PostShareView() {
            Row(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share icon",
                    tint = Color.Gray,
                    modifier = Modifier.clickable {  })
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "share",
                    style = MaterialTheme.typography.h5
                )
            }
        }

        @Composable
        fun PostReactionView(post:Post,navigator: DestinationsNavigator){
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                PostLikeView(noOfLikes = post.noOfLikes)
                PostCommentsView(post,navigator)
                PostShareView()
            }
        }

        @Composable
        fun UserInfoView(username:String, timestamp: String?, postTag: String?) {
            Row(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.width(5.dp))
                // User Image
                Image(
                    painter = painterResource(id = R.drawable.userpic),
                    contentDescription = "user pic",
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(44.dp)
                        .width(44.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    // Usrname
                    Text(
                        text = username,
                        style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(0.dp))

                    // Post Time
                    Text(
                        text = timestamp ?: "Public",
                        color = Color.Gray,
                        style = TextStyle(
                            fontWeight = FontWeight.W500),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .wrapContentSize()
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                // TAG
                if(postTag != null){
                    Box(modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 3.dp)
                        .clip(shape = RoundedCornerShape(3.dp))
                        .background(Color(0xFFBFE6F1)),

                        ) {
                        Text(
                            text = postTag.uppercase(),
                            color = Color(0xFF386BDD),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(10.dp,3.dp,10.dp,3.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.W600
                            )
                        )
                    }
                }
                Spacer(
                    Modifier
                        .weight(1f)
                        .background(Color.Green))
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically))

            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        @Composable
        fun PostItemLayout(post: Post, forHomeFeed:Boolean,navigator: DestinationsNavigator) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .wrapContentSize()) {

                // User info
                post.postDescription?.let {
                    post.tag?.let { it1 ->
                        UserInfoView(
                            username = post.postedBy,
                            post.postTime,
                            it1
                        )
                    }
                }

                // Post Description
                post.postDescription?.let {
                    Text(text = it,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .padding(top = 6.dp, start = 18.dp, end = 8.dp))
                }

                // Post Image (If posted with image)
                if(post.postImage != null){
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = post.postImage),
                        contentDescription = "Post Image"
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                if(forHomeFeed){
                    PostReactionView(post, navigator = navigator)
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                }
            }
        }

        @Composable
        fun TabLayout(tabitemList:List<String>) {
            var tabIndex by remember { mutableStateOf(0) } // 1.
            TabRow(
                selectedTabIndex = tabIndex
                ,backgroundColor = Color.White
                ,contentColor = Color.Blue
            ) {
                tabitemList.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = {
                            Text(
                                text = title,
                                color = if(tabIndex == index) Color.Blue else Color.Gray
                            )
                        }
                    )
                }
            }
        }

        @Composable
        fun postItem(post: Post,navigator: DestinationsNavigator) {
            PostItemLayout(
                post = post,
                forHomeFeed = false,
                navigator = navigator
            )
        }

        @Composable
        fun CommentItem(commentDescription:String,commentedBy:String,likesCount:Int) {
            var isChecked by remember{ mutableStateOf(false) }
            Column {
                UserInfoView(
                    // Username will be set from the list of comments that we
                    // will receive from the home screen an an argument
                    username = commentedBy,
                    timestamp = null,
                    postTag = null)

                // Actual comment
                // Will be set from the list of comments that we will receiver
                // from the home screen as an argument
                Text(
                    text = commentDescription,
                    modifier = Modifier.padding(start = 10.dp,0.dp,0.dp,8.dp))

                PostLikeView(noOfLikes = likesCount )
                Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
            }
        }
    }


}