package com.example.ktorclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktorclient.remote.PostService
import com.example.ktorclient.remote.dto.PostResponse
import com.example.ktorclient.ui.theme.KtorClientTheme
import java.util.*

class MainActivity : ComponentActivity() {
    private val service = PostService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = produceState<List<PostResponse>>(initialValue = emptyList() , producer ={
                value = service.getPost()
            } )
            KtorClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    LazyColumn{
                        items(posts.value){ index ->
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(text = index.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = index.body, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(20.dp))
                            }


                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorClientTheme {
        Greeting("Android")
    }
}