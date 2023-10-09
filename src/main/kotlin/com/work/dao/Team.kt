package com.work.dao

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object TeamTable : IdTable<Int>(name = "Team") {
    val name = varchar("name", 255)
    val url = varchar("url", 255)
    val isActive = bool("is_active").default(true)
    override val id: Column<EntityID<Int>> = integer("id").autoIncrement().entityId()
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class Team(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, Team>(TeamTable)

    var name by TeamTable.name
    var url by TeamTable.url
}

@Serializable
data class TeamDto(
    val id: Int? = null,
    val name: String,
    val url: String
)

fun Team.toDto(): TeamDto {
    return TeamDto(
        id = this.id.value,
        name = this.name,
        url = this.url
    )
}
