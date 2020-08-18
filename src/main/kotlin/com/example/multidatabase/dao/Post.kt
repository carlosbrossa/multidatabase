package com.example.multidatabase.dao

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        val id : Long? = null,
        val name: String
)

