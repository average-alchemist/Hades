package io.aethibo.entities.response

data class Thought(
    val id: Int? = null,
    var title: String,
    var content: String,
    val date: Long = System.currentTimeMillis()
)
