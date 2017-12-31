package com.journaler.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConfigApplication::class.java, *args)
}
