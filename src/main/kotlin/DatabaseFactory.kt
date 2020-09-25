package com.test.testsqlorac

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object DatabaseFactory {

    fun init() {
       /* Database.connect("jdbc:oracle:thin:@//address:1521/hct", driver = "oracle.jdbc.OracleDriver", user = "test", password = "test")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_READ_COMMITTED*/

        Database.connect(hikari())
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_READ_COMMITTED
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "oracle.jdbc.OracleDriver" // 1
        config.jdbcUrl = "jdbc:oracle:thin:@//192.158.22.1:1521/htt" // 2
        config.maximumPoolSize = 10
        config.isAutoCommit = false

        val user = "test" // 3
        if (user != null) {
            config.username = user
        }
        val password = "test" // 4
        if (password != null) {
            config.password = password
        }
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }
}