package com.example.ktorclient.remote

import com.example.ktorclient.remote.dto.PostRequest
import com.example.ktorclient.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImp(
   private val client: HttpClient
) : PostService {
    override suspend fun getPost(): List<PostResponse> {
            return try {
                client.get { url(HttpRouts.POSTS) }
            }catch (e : RedirectResponseException){
                println("Error 33:${e.response.status.description} ")
                emptyList()
            }catch (e : ClientRequestException){
                println("Error 22:${e.response.status.description} ")
                emptyList()
            }catch (e : ServerResponseException){
                println("Error 11:${e.response.status.description} ")
                emptyList()
            }
    }

    override suspend fun creatPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>() {
                url(HttpRouts.POSTS)
            contentType(ContentType.Application.Json)
                body =postRequest
            }
        }catch (e : RedirectResponseException){
            println("Error dfgdg:${e.response.status.description} ")
            null
        }catch (e : ClientRequestException){
            println("Error dfgdg:${e.response.status.description} ")
            null
        }catch (e : ServerResponseException){
            println("Error fdgfg:${e.response.status.description} ")
            null
        }
    }
}