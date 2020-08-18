package com.example.multidatabase.dao.configuration

object DBContextHolder {
    private val contextHolder: ThreadLocal<DbBrand> = ThreadLocal<DbBrand>()

    var currentDb: DbBrand
        get() {
            return contextHolder.get()
        }
        set(dbType) {
            contextHolder.set(dbType)
        }

    fun clear() {
        contextHolder.remove()
    }
}