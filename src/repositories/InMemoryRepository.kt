package io.aethibo.repositories

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.entities.response.User
import java.util.*

class InMemoryRepository : MainRepository {

    private val thoughts: MutableList<Thought> = mutableListOf<Thought>(
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Thought One",
            content = "This content is simply amazing",
            date = System.currentTimeMillis()
        ),
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Avengers Infinity war",
            content = "Need to check out that movie for real",
            date = System.currentTimeMillis()
        ),
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Hades",
            content = "Require some ktor work on this",
            date = System.currentTimeMillis()
        ),
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Apollo",
            content = "Android client for or thoughts application",
            date = System.currentTimeMillis()
        ),
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Bit of lorem",
            content = "Quas distinctio iure dicta. Inventore sunt et sed voluptates aperiam qui sit. Vitae dignissimos illum dolores. Dolor nam saepe ex reprehenderit labore. Non et beatae ad accusamus.",
            date = System.currentTimeMillis()
        ),
        Thought(
            id = UUID.randomUUID().toString(),
            title = "Bit of lorem but longer", content = """
            Quas distinctio iure dicta. Inventore sunt et sed voluptates aperiam qui sit. Vitae dignissimos illum dolores. Dolor nam saepe ex reprehenderit labore. Non et beatae ad accusamus.

            Odit non placeat sit necessitatibus illo quos iure et. Dolorum sed consequatur qui rerum enim. Sit quaerat rerum fugit.

            Voluptatum ipsam possimus corporis corrupti qui et aut. Laborum nihil fuga sunt. Omnis sequi deleniti natus earum assumenda aut qui occaecati. Unde corporis et quam.

            Dicta modi omnis iusto officia. Labore nulla esse aut in excepturi voluptatem aut similique. Id aut sit ex nam dolores.

            Architecto delectus omnis eum veritatis voluptate culpa impedit veritatis. Accusamus placeat rerum est dolores. Quia at praesentium blanditiis non est omnis. Aspernatur qui ex unde quisquam a ut.
        """.trimIndent(),
            date = System.currentTimeMillis()
        )
    )

    private val users: MutableList<User> = mutableListOf(
        User("gksčk123", "john@doe.com", "John Doe", "as123e+socvsške12e")
    )

    override suspend fun getAllThoughts(): List<Thought> = thoughts

    override suspend fun getThought(id: String): Thought? = thoughts.firstOrNull { it.id == id }

    override suspend fun addThought(draft: ThoughtDraft): Thought {

        val thought = Thought(
            id = UUID.randomUUID().toString(),
            title = "This is new thought",
            content = "amazing how adding new items is so easy using ktor",
            date = System.currentTimeMillis()
        )

        thoughts.add(thought)

        return thought
    }

    override suspend fun removeThought(id: String): Boolean = thoughts.removeIf { it.id == id }

    override suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean {

        val existingThought = thoughts.firstOrNull { it.id == id } ?: return false

        existingThought.title = draft.title
        existingThought.content = draft.content

        return true
    }

    override suspend fun addUser(user: User): User? {
        val newUser = User(user.id, user.email, user.displayName, user.passwordHash)

        users.add(newUser)

        return newUser
    }

    override suspend fun getUserById(userId: String): User? {
        return User("user#1", "john@doe.com", "John Doe", "j2wje312edlknčl20sfdd")
    }

    override suspend fun user(userId: String, hash: String?): User? {
        return User("user#1", "john@doe.com", "John Doe", "j2wje312edlknčl20sfdd")
    }
}