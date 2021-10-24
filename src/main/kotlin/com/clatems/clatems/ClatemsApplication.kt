package com.clatems.clatems

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClatemsApplication

fun main(args: Array<String>) {
    runApplication<ClatemsApplication>(*args)
}
