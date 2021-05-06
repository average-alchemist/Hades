package io.aethibo.repositories

import io.aethibo.entities.response.Thought

class InMemoryRepository : MainRepository {

    private val thoughts: MutableList<Thought> = mutableListOf<Thought>(
        Thought(title = "Thought One", content = "This content is simply amazing"),
        Thought(title = "Avengers Infinity war", content = "Need to check out that movie for real"),
        Thought(title = "Hades", content = "Require some ktor work on this"),
        Thought(title = "Apollo", content = "Android client for or thoughts application"),
        Thought(
            title = "Bit of lorem",
            content = "Quas distinctio iure dicta. Inventore sunt et sed voluptates aperiam qui sit. Vitae dignissimos illum dolores. Dolor nam saepe ex reprehenderit labore. Non et beatae ad accusamus."
        ),
        Thought(
            title = "Bit of lorem but longer", content = """
            Quas distinctio iure dicta. Inventore sunt et sed voluptates aperiam qui sit. Vitae dignissimos illum dolores. Dolor nam saepe ex reprehenderit labore. Non et beatae ad accusamus.

            Odit non placeat sit necessitatibus illo quos iure et. Dolorum sed consequatur qui rerum enim. Sit quaerat rerum fugit.

            Voluptatum ipsam possimus corporis corrupti qui et aut. Laborum nihil fuga sunt. Omnis sequi deleniti natus earum assumenda aut qui occaecati. Unde corporis et quam.

            Dicta modi omnis iusto officia. Labore nulla esse aut in excepturi voluptatem aut similique. Id aut sit ex nam dolores.

            Architecto delectus omnis eum veritatis voluptate culpa impedit veritatis. Accusamus placeat rerum est dolores. Quia at praesentium blanditiis non est omnis. Aspernatur qui ex unde quisquam a ut.
        """.trimIndent()
        )
    )

    override suspend fun getAllThoughts(): List<Thought> = thoughts

    override suspend fun getThought(id: Int): Thought? = thoughts.firstOrNull { it.id == id }

    override suspend fun addThought(draft: Thought): Thought {

        val thought = Thought(
            title = "This is new thought",
            content = "amazing how adding new items is so easy using ktor"
        )

        thoughts.add(thought)

        return thought
    }

    override suspend fun removeThought(id: Int): Boolean = thoughts.removeIf { it.id == id }

    override suspend fun updateThought(id: Int, thought: Thought): Boolean {

        val existingThought = thoughts.firstOrNull { it.id == id } ?: return false

        existingThought.title = thought.title
        existingThought.content = thought.content

        return true
    }
}