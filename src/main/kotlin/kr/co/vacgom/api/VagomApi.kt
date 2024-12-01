package kr.co.vacgom.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VacgomApi

fun main(args: Array<String>) {
    runApplication<VacgomApi>(*args)
}
