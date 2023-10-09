package com.work.dao

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime

object JokeTable : IdTable<String>(name = "joke") {
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val value = text("value")

    override val id: Column<EntityID<String>> = varchar("joke_id", 255).entityId()
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class Joke(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, Joke>(JokeTable)

    var createdAt by JokeTable.createdAt
    var updatedAt by JokeTable.updatedAt
    var value by JokeTable.value
}

fun Joke.toDto() = JokeDTO(this)

@Serializable
class JokeDTO(val value: String) {
    constructor(joke: Joke) : this(joke.value)
}
