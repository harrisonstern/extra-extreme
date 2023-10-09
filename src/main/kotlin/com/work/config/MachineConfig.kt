package com.work.config

import com.work.dao.TeamTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.nsk.kstatemachine.StateMachine


fun setupActiveTeamMachines (teamMachines: MutableList<StateMachine>) {


    transaction {
        TeamTable.select { TeamTable.url =="" }
    }
}
