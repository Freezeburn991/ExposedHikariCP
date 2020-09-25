package com.test.testsqlorac

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users : Table(){
    val userId : Column<Int> = integer("ID").autoIncrement().primaryKey()
    val email = varchar("EMAIL", 30).uniqueIndex()
    val displayName = varchar("DISPLAY_NAME", 50)
    val passwordHash = varchar("PASSWORD", 30)
}