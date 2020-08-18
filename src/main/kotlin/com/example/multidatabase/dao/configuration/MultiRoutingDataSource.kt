package com.example.multidatabase.dao.configuration

import com.example.multidatabase.dao.configuration.DBContextHolder.currentDb
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource


class MultiRoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return currentDb
    }
}