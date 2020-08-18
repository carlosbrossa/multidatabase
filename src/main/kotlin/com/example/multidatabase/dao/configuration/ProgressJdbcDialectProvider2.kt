package com.example.multidatabase.dao.configuration

import org.springframework.data.jdbc.repository.config.DialectResolver
import org.springframework.data.relational.core.dialect.Dialect
import org.springframework.jdbc.core.ConnectionCallback
import org.springframework.jdbc.core.JdbcOperations
import java.sql.Connection
import java.sql.SQLException
import java.util.*

class ProgressJdbcDialectProvider2 : DialectResolver.JdbcDialectProvider{
    override fun getDialect(operations: JdbcOperations): Optional<Dialect> {
        TODO("Not yet implemented")
    }


}