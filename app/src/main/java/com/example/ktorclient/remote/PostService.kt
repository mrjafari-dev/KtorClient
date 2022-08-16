package com.example.ktorclient.remote

import com.example.ktorclient.remote.dto.PostRequest
import com.example.ktorclient.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostService {
    suspend fun getPost() :List<PostResponse>
    suspend fun creatPost(postRequest: PostRequest):PostResponse?

    companion object{
        fun create():PostService{
            return PostServiceImp(
                client = HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}