package com.test.testsqlorac


import com.test.testsqlorac.DatabaseFactory.init
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun main() {

    init()

    var tran = transaction {
        Users.selectAll().map { toUsers(it) }
    }

    print(tran)
}

private fun toUsers(row: ResultRow): User =
    User(
        userId = row[Users.userId],
        email = row[Users.email],
        displayName = row[Users.displayName],
        passwordHash = row[Users.passwordHash]
    )