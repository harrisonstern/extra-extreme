import com.work.config.AppConfig
import org.flywaydb.core.Flyway

fun configureFlyway(appConfig: AppConfig) {
    val flyway = Flyway.configure()
        .dataSource(appConfig.database.url, appConfig.database.user, appConfig.database.password)
        .locations("classpath:db/migration")
        .baselineOnMigrate(true)
        .load()

    flyway.migrate()
}
