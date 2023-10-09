package com.work.di

import com.work.config.loadAppConfig
import com.work.services.TeamService
import org.koin.dsl.module
import ru.nsk.kstatemachine.StateMachine

val appModule = module {
    single { loadAppConfig() }
    single { TeamService() }
    single<MutableList<StateMachine>> { mutableListOf<StateMachine>() }
}
