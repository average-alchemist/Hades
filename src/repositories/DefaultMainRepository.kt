package io.aethibo.repositories

import io.aethibo.entities.request.SignUpDraft
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.entities.response.User
import io.aethibo.entities.tables.Thoughts
import io.aethibo.entities.tables.Users
import io.aethibo.entities.tables.Users.toUser
import io.aethibo.repositories.utils.DatabaseFactory.dbQuery
import io.aethibo.utils.hash
import org.jetbrains.exposed.sql.*
import java.util.*

/**
 * H2 repository is in memory repository
 * but it allows us to use actual data
 */
class DefaultMainRepository : MainRepository {

    override suspend fun getAllThoughts(): List<Thought> = dbQuery {
        Thoughts.selectAll()
            .map { Thoughts.toThought(it) }
    }

    override suspend fun getThought(id: String): Thought? = dbQuery {
        Thoughts.select { Thoughts.id eq id }
            .mapNotNull { Thoughts.toThought(it) }
            .singleOrNull()
    }

    override suspend fun addThought(draft: ThoughtDraft): Thought? = dbQuery {
        val insertThought = Thoughts.insert {
            it[id] = UUID.randomUUID().toString()
            it[title] = draft.title
            it[content] = draft.content
            it[date] = System.currentTimeMillis()
        }

        val result = insertThought.resultedValues?.get(0)

        if (result != null) Thoughts.toThought(result)
        else null
    }

    override suspend fun removeThought(id: String): Boolean = dbQuery {
        val deletedRows: Int = Thoughts.deleteWhere { Thoughts.id eq id }

        deletedRows > 0
    }

    override suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean = dbQuery {
        val updatedRows: Int = Thoughts.update({ Thoughts.id eq id }) {
            it[title] = draft.title
            it[content] = draft.content
        }

        updatedRows > 0
    }

    override suspend fun getUserById(userId: String): User? = dbQuery {
        Users.select { Users.id eq userId }
            .map { User(userId, it[Users.email], it[Users.displayName], it[Users.passwordHash]) }
            .singleOrNull()
    }

    override suspend fun createUser(draft: SignUpDraft): User? = dbQuery {
        val insertUser = Users.insert {
            it[id] = UUID.randomUUID().toString()
            it[email] = draft.email
            it[displayName] = draft.displayName
            it[passwordHash] = hash(draft.password)
        }

        val result = insertUser.resultedValues?.get(0)

        if (result != null) toUser(result)
        else null
    }

    override suspend fun user(userId: String, hash: String?): User? {
        val user = dbQuery {
            Users.select { Users.id eq userId }
                .mapNotNull { toUser(it) }
                .singleOrNull()
        }

        return when {
            user == null -> null
            hash == null -> user
            user.passwordHash == hash -> user
            else -> null
        }
    }
}