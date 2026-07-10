package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AetherApplication

fun main(args: Array<String>) {
	runApplication<AetherApplication>(*args)
}
