package kr.co.vacgom.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["kr.co.vacgom.persistence"])
class VacgomApi

fun main(args: Array<String>) {
    runApplication<VacgomApi>(*args)
}
