package io.aethibo.entities.tables

import io.aethibo.entities.response.Thought
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Thoughts : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val title: Column<String> = varchar("title", 100)
    val content: Column<String> = varchar("content", 500)

    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "PK_Thoughts_ID")

    fun toThought(row: ResultRow): Thought =
        Thought(
            id = row[id],
            title = row[title],
            content = row[content]
        )
}