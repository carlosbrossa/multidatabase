package com.example.multidatabase.dao.configuration

import org.hibernate.LockOptions
import org.springframework.data.relational.core.dialect.Dialect
import org.hibernate.dialect.FirebirdDialect
import org.springframework.data.relational.core.dialect.AnsiDialect
import org.springframework.data.relational.core.dialect.ArrayColumns
import org.springframework.data.relational.core.dialect.LimitClause
import org.springframework.data.relational.core.dialect.LockClause
import org.springframework.data.relational.core.sql.render.SelectRenderContext


class CustomDialect : Dialect {

    override fun lock(): LockClause {
        TODO("Not yet implemented")
    }

    override fun limit(): LimitClause {
        TODO("Not yet implemented")
    }

    override fun getSelectContext(): SelectRenderContext {
        TODO("Not yet implemented")
    }

}