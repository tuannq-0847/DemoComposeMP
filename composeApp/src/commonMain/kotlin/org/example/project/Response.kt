package org.example.project

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: String,
    @SerialName("title")
    val title: String,
    @SerialName("completed")
    val completed: Boolean,
    val type: Int = 1
)