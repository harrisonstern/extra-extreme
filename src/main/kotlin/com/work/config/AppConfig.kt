@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

package com.work.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource


data class Database(val url:String, val user: String, val password: String, val maxPoolSize:Int)
data class AppConfig(val env: String, val database: Database)

fun loadAppConfig(): AppConfig {
    return ConfigLoaderBuilder.default()
        .addResourceSource("/application.conf")
        .build()
        .loadConfigOrThrow<AppConfig>()
}
