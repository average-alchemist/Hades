package io.aethibo.entities.response

data class Thought(
    val id: String,
    var title: String,
    var content: String,
    val date: Long
)
