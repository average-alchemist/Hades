package io.aethibo.entities.response

import java.util.*

data class Thought(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var content: String,
    val date: Long = System.currentTimeMillis()
)
