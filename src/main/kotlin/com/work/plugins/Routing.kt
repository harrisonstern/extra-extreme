package com.work.plugins

import com.work.dao.Joke
import com.work.dao.JokeTable
import com.work.dao.toDto
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {

    routing {

        configureTeamRouting()

        get("/") {
            call.respondText("Hello World!")

        }

        get("/joke") {
            var joke = transaction {
                Joke.wrapRows(JokeTable.selectAll()).toList().map { it.toDto() }
            }
            call.respond(joke)

        }
    }
}
