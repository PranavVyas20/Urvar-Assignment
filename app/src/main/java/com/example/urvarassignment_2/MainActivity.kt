package com.example.urvarassignment_2

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urvarassignment_2.data.Post
import com.example.urvarassignment_2.ui.theme.UrvarAssignment_2Theme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UrvarAssignment_2Theme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Destination(start = true)
@Composable
fun FirstScreen(navigator: DestinationsNavigator) {
//    val context = LocalContext.current
//    val mPostViewModel: PostViewModel = viewModel(
//        factory = PostViewModelFactory(context.applicationContext as Application)
//    )
//    var postListFromDb = mPostViewModel.readAllData

    val postList = MyComposables.posList

    Column() {
        MyComposables.TabLayout(tabitemList = MyComposables.tabItemList)
        LazyColumn{
            items(postList){ post ->
                MyComposables.PostItemLayout(
                    post = post , forHomeFeed = true,navigator)
            }
        }
    }
}

@Destination
@Composable
fun SecondScreen(post:Post,navigator: DestinationsNavigator) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Comments",
                    style = MaterialTheme.typography.h3)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = {
            Column() {
                // User Post
                MyComposables.postItem(post = post, navigator = navigator)

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, 0.dp, 12.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "${post.comments.size} comments",
                        style = MaterialTheme.typography.h5)

                    // Contains comments count and unfold icon button
                    Row(
                        verticalAlignment = Alignment.Top)
                    {
                        IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .height(20.dp)
                                .width(23.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.UnfoldMore,
                                contentDescription = "unfold icon",
                                tint = Color(0xFF386BDD))
                        }
                        Text(
                            text = "Recent",
                            color = Color(0xFF386BDD),
                            style = MaterialTheme.typography.h5)
                    }

                }
                LazyColumn{
                    items(post.comments){ pst ->
                        MyComposables.CommentItem(
                            commentDescription = pst,
                            commentedBy = "Pranav Vyas",post.noOfLikes)
                    }
                }
            }
        })


}

@Preview
@Composable
fun test(){
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = {  },
            modifier = Modifier.width(25.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.UnfoldMore,
                contentDescription = "unfold icon",
//                modifier = Modifier.,
                tint = Color(0xFF386BDD))
        }
        Text(
            text = "Recent",
            color = Color(0xFF386BDD),
            style = MaterialTheme.typography.h5)
    }
}



