package com.example.multidatabase.dao.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.example.multidatabase"], entityManagerFactoryRef = "multiEntityManager", transactionManagerRef = "multiTransactionManager")
class PersistenceConfiguration {
    private val PACKAGE_SCAN = "com.example.multidatabase"

    @Primary
    @Bean(name = ["mainDataSource"])
    @ConfigurationProperties("app.datasource.main")
    fun mainDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean(name = ["clientADataSource"])
    @ConfigurationProperties("app.datasource.clienta")
    fun clientADataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean(name = ["clientBDataSource"])
    @ConfigurationProperties("app.datasource.clientb")
    fun clientBDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean(name = ["multiRoutingDataSource"])
    fun multiRoutingDataSource(): DataSource {
        val targetDataSources: MutableMap<Any, Any> = HashMap()
        targetDataSources[DbBrand.DB1] = mainDataSource()
        targetDataSources[DbBrand.DB2] = clientADataSource()
        targetDataSources[DbBrand.DB3] = clientBDataSource()
        val multiRoutingDataSource = MultiRoutingDataSource()
        multiRoutingDataSource.setDefaultTargetDataSource(mainDataSource())
        multiRoutingDataSource.setTargetDataSources(targetDataSources)
        return multiRoutingDataSource
    }

    @Bean(name = ["multiEntityManager"])
    fun multiEntityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = multiRoutingDataSource()
        em.setPackagesToScan(PACKAGE_SCAN)
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaProperties(hibernateProperties())
        return em
    }

    @Bean(name = ["multiTransactionManager"])
    fun multiTransactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = multiEntityManager().getObject()
        return transactionManager
    }

    @Primary
    @Bean(name = ["dbSessionFactory"])
    fun dbSessionFactory(): LocalSessionFactoryBean {
        val sessionFactoryBean = LocalSessionFactoryBean()
        sessionFactoryBean.setDataSource(multiRoutingDataSource())
        sessionFactoryBean.setPackagesToScan(PACKAGE_SCAN)
        sessionFactoryBean.hibernateProperties = hibernateProperties()
        return sessionFactoryBean
    }

    private fun hibernateProperties(): Properties {
        val properties = Properties()
        properties.put("hibernate.show_sql", true)
        properties.put("hibernate.format_sql", true)
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
        return properties
    }
}