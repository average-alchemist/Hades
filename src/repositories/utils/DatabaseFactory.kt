package io.aethibo.repositories.utils

import io.aethibo.entities.tables.Thoughts
import io.aethibo.entities.tables.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    init {
        // In memory / keep alive between connections/transactions
        // Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

        // PostgreSQL
        Database.connect(
            url = "jdbc:postgresql://database:5432/thoughtsDb",
            driver = "org.postgresql.Driver",
            user = "username",
            password = "secret"
        )

        /**
         * Hikari setup - with connection pooling
         */
        /*val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://database:5432/thoughtsDb"
            driverClassName = "org.postgresql.Driver"
            username = "username"
            password = "secret"
            maximumPoolSize = 10
        }

        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)*/

        transaction {
            SchemaUtils.create(Thoughts)
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(
        block: () -> T
    ): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}