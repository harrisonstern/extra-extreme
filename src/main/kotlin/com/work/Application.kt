package com.work

import com.work.config.AppConfig
import com.work.config.connectToDatabase
import com.work.di.appModule
import com.work.plugins.*
import configureFlyway
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    val appConfig by inject<AppConfig>()
    connectToDatabase(appConfig)
    configureFlyway(appConfig)

    configureSerialization()
    configureRouting()
}
