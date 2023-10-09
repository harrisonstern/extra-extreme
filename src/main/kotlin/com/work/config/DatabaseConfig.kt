package com.work.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun connectToDatabase(appConfig: AppConfig) = Database.connect(hikari(appConfig))

fun hikari(appConfig: AppConfig): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = "com.mysql.cj.jdbc.Driver"
    config.jdbcUrl = "${appConfig.database.url}?useUnicode=true&characterEncoding=UTF-8"
    config.username = appConfig.database.user
    config.password = appConfig.database.password
    config.maximumPoolSize = appConfig.database.maxPoolSize
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"

    // Suggestions from https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
    config.addDataSourceProperty("cachePrepStmts", "true")
    config.addDataSourceProperty("prepStmtCacheSize", "250")
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
    config.addDataSourceProperty("useServerPrepStmts", "true")
    config.addDataSourceProperty("useLocalSessionState", "true")
    config.addDataSourceProperty("rewriteBatchedStatements", "true")
    config.addDataSourceProperty("cacheResultSetMetadata", "true")
    config.addDataSourceProperty("cacheServerConfiguration", "true")
    config.addDataSourceProperty("elideSetAutoCommits", "true")
    config.addDataSourceProperty("maintainTimeStats", "false")

    config.validate()
    return HikariDataSource(config)
}
