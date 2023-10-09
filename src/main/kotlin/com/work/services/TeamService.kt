package com.work.services

import com.work.dao.TeamDto
import com.work.dao.TeamTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.getScopeId

class TeamService {

    fun registerTeam(team: TeamDto) {
        val ret = transaction {
            TeamTable.insert {
                it[name] = team.name
                it[url] = team.url
            }
        }


        println(message = "HELLO: ${ret.resultedValues}")
    }

}
