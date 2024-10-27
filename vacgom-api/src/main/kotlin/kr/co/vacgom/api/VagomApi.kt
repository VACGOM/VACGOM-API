package kr.co.vacgom.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["kr.co.vacgom"])
@EntityScan(basePackages = ["kr.co.vacgom.persistence"])
class VacgomApi

fun main(args: Array<String>) {
    runApplication<VacgomApi>(*args)
}
