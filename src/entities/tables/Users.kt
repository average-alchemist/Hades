package io.aethibo.entities.tables

import io.aethibo.entities.response.User
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id: Column<String> = varchar("id", 50)
    val email: Column<String> = varchar("email", 128).uniqueIndex()
    val displayName: Column<String> = varchar("display_name", 256)
    val passwordHash: Column<String> = varchar("password_hash", 64)

    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "PK_Users_ID")

    fun toUser(row: ResultRow): User =
        User(
            id = row[id],
            email = row[email],
            displayName = row[displayName],
            passwordHash = row[passwordHash]
        )
}