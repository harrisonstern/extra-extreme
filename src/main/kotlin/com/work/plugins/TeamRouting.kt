package com.work.plugins

import com.work.config.AppConfig
import com.work.dao.Team
import com.work.dao.TeamDto
import com.work.dao.TeamTable
import com.work.services.TeamService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject
import ru.nsk.kstatemachine.*


private object MinimalSyntaxSample {
    object SwitchEvent : Event
}


fun Routing.configureTeamRouting() {

    val teamService by inject<TeamService>()

    val teamMachines by inject<MutableList<StateMachine>>()

    post("/team/register") {
        val team = call.receive<TeamDto>()

        launch {
            val machine = createStateMachine(this) {
                // State machine finishes when enters final state
                val redState = finalState()

                val yellowState = state {
                    // Setup transition
                    transition<MinimalSyntaxSample.SwitchEvent> {
                        targetState = redState
                        // Add transition listener
                        onTriggered { println("Transition triggered") }
                    }
                }

                initialState("green") {
                    // Add state listeners
                    onEntry { println("Enter $name") }
                    onExit { println("Exit $name") }

                    transition<MinimalSyntaxSample.SwitchEvent>(targetState = yellowState)
                }

                onFinished { println("Finished") }
            }

            teamMachines.add(machine)


            call.respond(teamService.registerTeam(team))

        }
    }

    get("/team/check"){
       println(teamMachines)
        call.respond("HELLO")
    }

}

fun <T> transition(t: T) {

}



