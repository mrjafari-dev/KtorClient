package com.example.ktorclient.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PostResponse(
    val userId :Int,
    val id :Int,
    val title :String,
    val body :String ,


)
