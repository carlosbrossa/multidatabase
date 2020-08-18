package com.example.multidatabase

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan("com.example.multidatabase")
@SpringBootApplication
class MultidatabaseApplication

fun main(args: Array<String>) {
	runApplication<MultidatabaseApplication>(*args)
}
