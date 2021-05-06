package io.aethibo.repositories

import io.aethibo.entities.response.Thought
import io.aethibo.entities.tables.Thoughts
import io.aethibo.repositories.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

/**
 * H2 repository is in memory repository
 * but it allows us to use actual data
 */
class H2Repository : MainRepository {

    override suspend fun getAllThoughts(): List<Thought> = dbQuery {
        Thoughts.selectAll()
            .map { Thoughts.toThought(it) }
    }

    override suspend fun getThought(id: Int): Thought? = dbQuery {
        Thoughts.select { Thoughts.id eq id }
            .mapNotNull { Thoughts.toThought(it) }
            .singleOrNull()
    }

    override suspend fun addThought(draft: Thought): Thought? = dbQuery {
        val insertThought = Thoughts.insert {
            it[title] = draft.title
            it[content] = draft.content
        }

        val result = insertThought.resultedValues?.get(0)

        if (result != null) Thoughts.toThought(result)
        else null
    }

    override suspend fun removeThought(id: Int): Boolean = dbQuery {
        val deletedRows: Int = Thoughts.deleteWhere { Thoughts.id eq id }

        deletedRows > 0
    }

    override suspend fun updateThought(id: Int, thought: Thought): Boolean = dbQuery {
        val updatedRows: Int = Thoughts.update({ Thoughts.id eq id }) {
            it[title] = thought.title
            it[content] = thought.content
        }

        updatedRows > 0
    }
}